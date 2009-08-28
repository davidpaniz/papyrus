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
		public function ClientService()
		{
			super( "clientService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listClient( callback:Function ):void
		{
			listCallback = callback;
			service.listClient( );
		}
 		public function listClientResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function saveClient( client:Client, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.saveClient( client );
		}
		
		public function saveClientResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as Client);
				
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
		
		private var deleteCallback:Function;
		public function deleteClient( client:Client, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteClient( client );
		}

		public function deleteClientResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as Client);
				
			ClientComboBox.reset();
		}
	}
}