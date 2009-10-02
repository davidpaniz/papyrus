package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.model.BusinessRule;
	import org.papyrus.model.Condition;

	public class BusinessRuleService extends Service
	{
		public function BusinessRuleService(callBackFunction:Function)
		{
			super( "businessRuleService", callBackFunction );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listBusinessRule():void
		{
			service.listBusinessRule( );
		}
 		public function listBusinessRuleResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function saveBusinessRule( businessRule:BusinessRule ):void
		{
			service.saveBusinessRule( businessRule );
		}
		
		public function saveBusinessRuleResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as BusinessRule);
		}
		

		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deleteBusinessRule( businessRule:BusinessRule ):void
		{
			service.deleteBusinessRule( businessRule );
		}

		public function deleteBusinessRuleResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as BusinessRule);
		}
	}
}