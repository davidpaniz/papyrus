package org.papyrus.components.notification
{
	import mx.containers.VBox;

	public class NotificationBase extends VBox
	{
		import mx.events.EffectEvent;
		import mx.effects.Move;
		import mx.effects.Fade;
		import mx.effects.Effect;

		private var nexty:int;

 	    [Embed("icons/InfoOk.png")]
		private static const ICON_DEFAULT:Class;
		[Embed("icons/InfoError.png")]
		public static const ICON_ERROR:Class;
		[Embed("icons/InfoWarn.png")]
		private static const ICON_WARN:Class;

		[Bindable] public var text  : String;
		[Bindable] public var type  : NotificationType;
		[Bindable] public var image : Class;

		private var moveFx:Move = new Move();
		private var hideFx:Fade = new Fade();

		public var modal:Boolean = false;

		public function NotificationBase()
		{
			moveFx.duration = 250;
			hideFx.duration = 250;

			setStyle( "moveEffect", moveFx );
			setStyle( "hideEffect", hideFx );

			moveFx.addEventListener( EffectEvent.EFFECT_END, function( event:Event ):void {
				y = nexty || y;
			} );

			var ref:NotificationBase = this;
			hideFx.addEventListener( EffectEvent.EFFECT_END, function( event:Event ):void {
				dispatchEvent( new Event( "close" ) );

				if( parent && ref )
					parent.removeChild( ref );
			} );
		}

		public static function createNotification( type:NotificationType, text:String ):NotificationBase
		{
			var notification:Notification = new Notification();
			return create( notification, type, text );
		}
		public static function createAskNotification( type:NotificationType, text:String, handler:Function ):NotificationBase
		{
			var notification:AskNotification = new AskNotification();
			notification.confirmHandler = handler;
			return create( notification, type, text );
		}
		public static function createNotificationWithFunction( type:NotificationType, text:String, handler:Function ):NotificationBase
		{
			var notification:NotificationWithFunction = new NotificationWithFunction();
			notification.confirmHandler = handler;
			return create( notification, type, text );
		}
		private static function create( notification:NotificationBase, type:NotificationType, text:String ):NotificationBase
		{
			notification.styleName = type.style;
			notification.text      = text;
			notification.type      = type;
			
			if(type == NotificationType.DEFAULT)
				notification.image = ICON_DEFAULT;
			else if(type == NotificationType.ERROR)
				notification.image = ICON_ERROR;
			else if(type == NotificationType.WARN)
				notification.image = ICON_WARN;
				
			return notification;
		}

		public function close():void
		{
			if( hideFx.isPlaying == false )
				visible = false;
		}

		public function setY( y:int ):void
		{
			if( moveFx.isPlaying == true )
				this.nexty = y;
			else
			{
				this.y = y;
				this.nexty = undefined;
			}
		}
	}
}