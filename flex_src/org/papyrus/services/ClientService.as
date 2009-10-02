package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.combobox.ClientComboBox;
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Client;

	public class ClientService extends Service
	{
		public function ClientService(callBackFunction:Function)
		{
			super( "clientService", callBackFunction );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listClient():void
		{
			service.listClient( );
		}
 		public function listClientResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
 		public function listMyIncidents(status:String, initialDate:Date, endDate:Date):void
		{
			service.listMyIncidents( status, initialDate, endDate );
		}
 		public function listMyIncidentsResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function saveClient( client:Client):void
		{
			service.saveClient( client );
		}
		
		public function saveClientResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Client);
			ClientComboBox.reset();
		}
		
		public function saveClientFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deleteClient( client:Client):void
		{
			service.deleteClient( client );
		}

		public function deleteClientResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Client);
				
			ClientComboBox.reset();
		}
	}
}