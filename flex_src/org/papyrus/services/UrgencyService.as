package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.combobox.UrgencyComboBox;
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Urgency;

	public class UrgencyService extends Service
	{
		public function UrgencyService()
		{
			super( "urgencyService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listUrgency( callback:Function ):void
		{
			listCallback = callback;
			service.listUrgency( );
		}
 		public function listUrgencyResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function saveUrgency( urgency:Urgency, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.saveUrgency( urgency );
		}
		
		public function saveUrgencyResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as Urgency);
				
			UrgencyComboBox.reset();
		}
		
		public function saveUrgencyFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		private var deleteCallback:Function;
		public function deleteUrgency( urgency:Urgency, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteUrgency( urgency );
		}

		public function deleteUrgencyResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as Urgency);
				
			UrgencyComboBox.reset();
		}
	}
}