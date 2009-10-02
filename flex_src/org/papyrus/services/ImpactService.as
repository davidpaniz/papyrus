package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.combobox.ImpactComboBox;
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Impact;

	public class ImpactService extends Service
	{
		public function ImpactService(callBackFunction:Function)
		{
			super( "impactService", callBackFunction );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listImpact():void
		{
			service.listImpact();
		}
 		public function listImpactResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function saveImpact( impact:Impact ):void
		{
			service.saveImpact( impact );
		}
		
		public function saveImpactResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Impact);
				
			ImpactComboBox.reset();
		}
		

		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deleteImpact( impact:Impact ):void
		{
			service.deleteImpact( impact );
		}

		public function deleteImpactResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Impact);
				
			ImpactComboBox.reset();
		}
	}
}