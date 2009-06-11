package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Priority;

	public class PriorityService extends Service
	{
		public function PriorityService()
		{
			super( "priorityService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listPriority( callback:Function ):void
		{
			listCallback = callback;
			service.listPriority( );
		}
 		public function listPriorityResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function savePriority( priority:Priority, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.savePriority( priority );
		}
		
		public function savePriorityResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as Priority);
		}
		
		public function savePriorityFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		private var deleteCallback:Function;
		public function deletePriority( priority:Priority, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deletePriority( priority );
		}

		public function deletePriorityResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as Priority);
		}
		
		/*************************************
		 * 
		 * ***********************************/

	}
}