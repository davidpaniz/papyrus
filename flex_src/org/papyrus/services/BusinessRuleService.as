package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.model.BusinessRule;

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
		
 		public function loadBusinessRule(businessRule:BusinessRule):void
		{
			service.loadBusinessRule( businessRule.id );
		}
 		public function loadBusinessRuleResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as BusinessRule );
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