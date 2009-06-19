package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Incident;

	public class IncidentService extends Service
	{
		public function IncidentService()
		{
			super( "incidentService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listIncident( callback:Function ):void
		{
			listCallback = callback;
			service.listIncident( );
		}
 		public function listIncidentResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function saveIncident( incident:Incident, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.saveIncident( incident );
		}
		
		public function saveIncidentResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as Incident);
		}
		
		public function saveIncidentFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		private var deleteCallback:Function;
		public function deleteIncident( incident:Incident, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteIncident( incident );
		}

		public function deleteIncidentResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as Incident);
		}
	}
}