package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Priority;

	public class PriorityService extends Service
	{
		public function PriorityService(callBackFunction:Function)
		{
			super( "priorityService", callBackFunction );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listPriority():void
		{
			service.listPriority( );
		}
 		public function listPriorityResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function savePriority( priority:Priority ):void
		{
			service.savePriority( priority );
		}
		
		public function savePriorityResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Priority);
		}
		
		public function savePriorityFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deletePriority( priority:Priority ):void
		{
			service.deletePriority( priority );
		}

		public function deletePriorityResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Priority);
		}
	}
}