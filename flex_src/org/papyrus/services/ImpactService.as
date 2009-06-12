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
		public function ImpactService()
		{
			super( "impactService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listImpact( callback:Function ):void
		{
			listCallback = callback;
			service.listImpact( );
		}
 		public function listImpactResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function saveImpact( impact:Impact, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.saveImpact( impact );
		}
		
		public function saveImpactResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as Impact);
				
			ImpactComboBox.reset();
		}
		
		public function saveImpactFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		private var deleteCallback:Function;
		public function deleteImpact( impact:Impact, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteImpact( impact );
		}

		public function deleteImpactResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as Impact);
				
			ImpactComboBox.reset();
		}
	}
}