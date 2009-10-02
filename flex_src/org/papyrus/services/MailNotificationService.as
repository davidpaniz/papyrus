package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.MailNotification;

	public class MailNotificationService extends Service
	{
		public function MailNotificationService(callBackFunction:Function)
		{
			super( "mailNotificationService", callBackFunction );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listMailNotification():void
		{
			service.listMailNotification();
		}
 		public function listMailNotificationResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function createMailNotification( mailNotification:MailNotification ):void
		{
			service.createMailNotification( mailNotification );
		}
		
		public function createMailNotificationResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as MailNotification);
		}
		
		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deleteMailNotification( mailNotification:MailNotification ):void
		{
			service.deleteMailNotification( mailNotification );
		}

		public function deleteMailNotificationResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as MailNotification);
		}
	}
}