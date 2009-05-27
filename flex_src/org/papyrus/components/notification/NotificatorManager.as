package org.papyrus.components.notification
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.geom.Point;
	import flash.utils.setTimeout;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.FlexMouseEvent;
	import mx.managers.PopUpManager;

	public class NotificatorManager
	{
		public static const MARGIN:int = 5;

		public static const DEFAULT_DELAY          : int = 5000;
		public static const DEFAULT_DELAY_IN_PLACE : int = 20000;

		public static var DEFAULT_WIDTH  : int = 200;
		public static var DEFAULT_HEIGHT : int = 100;

		private static var currentNotifications:ArrayCollection = new ArrayCollection();

		public static function notifyInPlace( text:String, container:UIComponent, delay:int = DEFAULT_DELAY_IN_PLACE ):void
		{
			doNotifyInPlace( NotificationType.DEFAULT, text, container, delay );
		}
		
		public static function ask( text:String, handler:Function):NotificationBase
		{
			var notification:NotificationBase = NotificationBase.createAskNotification( NotificationType.ASK, text, handler );
			showNotification( notification );

			return notification;
		}
		
		public static function notificationWithFunction( text:String, handler:Function):NotificationBase
		{
			var notification:NotificationBase = NotificationBase.createNotificationWithFunction( NotificationType.ERROR, text, handler );
			showNotification( notification );

			return notification;
		}

		public static function notify( text:String, delay:int = DEFAULT_DELAY ):void
		{
			doNotify( NotificationType.DEFAULT, text, delay );
		}
		public static function warn( text:String, delay:int = DEFAULT_DELAY ):void
		{
			doNotify( NotificationType.WARN, text, delay );
		}
		public static function error( text:String, delay:int = DEFAULT_DELAY ):void
		{
			doNotify( NotificationType.ERROR, text, delay );
		}

		private static function doNotify( type:NotificationType, text:String, delay:int ):void
		{
			var notification:NotificationBase = NotificationBase.createNotification( type, text );
			showNotification( notification, delay );
		}
		private static function showNotification( notification:NotificationBase, delay:int = -1 ):void
		{
			currentNotifications.addItemAt( notification, 0 );

			// importante ser nesta ordem
			var app:DisplayObject = Application.application as DisplayObject;

			notification.width     = DEFAULT_WIDTH;
			notification.minHeight = DEFAULT_HEIGHT;

			notification.x = app.width / 2 - DEFAULT_WIDTH / 2;
			notification.y = DEFAULT_HEIGHT * -1; // acima da tela

			notification.addEventListener( "close", function( event:Event ):void 
			{
				close(notification);
			} );

            notification.addEventListener(FlexMouseEvent.MOUSE_DOWN_OUTSIDE, function( event:Event ):void 
			{
				close(notification);
			} );
            
            notification.addEventListener(FlexMouseEvent.MOUSE_WHEEL_OUTSIDE, function( event:Event ):void 
			{
				close(notification);
			} );

			if( delay > 0 )
				setTimeout( notification.close, delay );

			notification.addEventListener( FlexEvent.CREATION_COMPLETE, function( event:Object ):void {
				reposition();
			} );

			PopUpManager.addPopUp( notification, app, notification.modal );
		}

		private static function reposition():void
		{
			var count:int = 0;
			for each( var notification:NotificationBase in currentNotifications )
				notification.setY( count++ * ( notification.height + MARGIN ) );
		}

		private static function doNotifyInPlace( type:NotificationType, text:String, container:UIComponent, delay:int ):void
		{
			var notification:NotificationBase = NotificationBase.createNotification( type, text );

			notification.percentWidth = 100;
			notification.percentHeight = 100;

			container.addChild( notification );

			container.visible = true;
			container.includeInLayout = true;

			if( delay > 0 )
				setTimeout( notification.close, delay );
		}
		
		private static function close(notification:NotificationBase):void
		{
			if(currentNotifications.getItemIndex( notification ) != -1)
				currentNotifications.removeItemAt( currentNotifications.getItemIndex( notification ) );
				
			PopUpManager.removePopUp( notification );
			reposition();
		}
	}
}