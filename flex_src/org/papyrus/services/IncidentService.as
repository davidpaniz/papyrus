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
		
		private var createCallback:Function;
		public function createIncident( incident:Incident, callbackFunction:Function ):void
		{
			this.createCallback = callbackFunction;
			service.createIncident( incident );
		}
		
		public function createIncidentResult( event:ResultEvent ):void
		{
			if(createCallback != null)
				createCallback(event.result as Incident);
		}
		
		private var updateCallback:Function;
		public function updateIncident( incident:Incident, callbackFunction:Function ):void
		{
			updateCallback = callbackFunction;
			service.updateIncident( incident );
		}
		
		public function updateIncidentResult( event:ResultEvent ):void
		{
			if(updateCallback != null)
				updateCallback(event.result as Incident);
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