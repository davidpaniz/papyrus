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
		public function UrgencyService(callBackFunction:Function)
		{
			super( "urgencyService", callBackFunction );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listUrgency():void
		{
			service.listUrgency( );
		}
 		public function listUrgencyResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function saveUrgency( urgency:Urgency ):void
		{
			service.saveUrgency( urgency );
		}
		
		public function saveUrgencyResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Urgency);
				
			UrgencyComboBox.reset();
		}

		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deleteUrgency( urgency:Urgency ):void
		{
			service.deleteUrgency( urgency );
		}

		public function deleteUrgencyResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Urgency);
				
			UrgencyComboBox.reset();
		}
	}
}