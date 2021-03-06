package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.model.Incident;
	import org.papyrus.model.User;

	public class IncidentService extends Service
	{
		public function IncidentService(callback:Function)
		{
			super( "incidentService", callback );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listIncident():void
		{
			service.listIncident( );
		}
 		public function listIncidentResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function createIncident( incident:Incident ):void
		{
			service.createIncident( incident );
		}
		
		public function createIncidentResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Incident);
		}
		
		public function updateIncident( incident:Incident ):void
		{
			service.updateIncident( incident );
		}
		
		public function updateIncidentResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Incident);
		}
		
		public function assignIncident( incident:Incident, user:User ):void
		{
			service.assignIncident( incident, user );
		}
		
		public function assignIncidentResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Incident);
		}
		
		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deleteIncident( incident:Incident ):void
		{
			service.deleteIncident( incident );
		}

		public function deleteIncidentResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Incident);
		}
	}
}