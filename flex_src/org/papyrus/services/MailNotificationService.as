package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.MailNotification;

	public class MailNotificationService extends Service
	{
		public function MailNotificationService()
		{
			super( "mailNotificationService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listMailNotification( callback:Function ):void
		{
			listCallback = callback;
			service.listMailNotification( );
		}
 		public function listMailNotificationResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var createCallback:Function;
		public function createMailNotification( mailNotification:MailNotification, callbackFunction:Function ):void
		{
			this.createCallback = callbackFunction;
			service.createMailNotification( mailNotification );
		}
		
		public function createMailNotificationResult( event:ResultEvent ):void
		{
			if(createCallback != null)
				createCallback(event.result as MailNotification);
		}
		
		public function createMailNotificationFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		private var deleteCallback:Function;
		public function deleteMailNotification( mailNotification:MailNotification, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteMailNotification( mailNotification );
		}

		public function deleteMailNotificationResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as MailNotification);
		}
	}
}