package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.model.BusinessRule;
	import org.papyrus.model.Condition;

	public class BusinessRuleService extends Service
	{
		public function BusinessRuleService()
		{
			super( "businessRuleService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listBusinessRule( callback:Function ):void
		{
			listCallback = callback;
			service.listBusinessRule( );
		}
 		public function listBusinessRuleResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function saveBusinessRule( businessRule:BusinessRule, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.saveBusinessRule( businessRule );
		}
		
		public function saveBusinessRuleResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as BusinessRule);
		}
		

		/*************************************
		 * DELETE 
		 * ***********************************/
		
		private var deleteCallback:Function;
		public function deleteBusinessRule( businessRule:BusinessRule, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteBusinessRule( businessRule );
		}

		public function deleteBusinessRuleResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as BusinessRule);
		}
		
		/*************************************
		 * 
		 * ***********************************/

	}
}