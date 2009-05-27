package org.papyrus.components.notification
{
	public class NotificationType
	{
		public static const DEFAULT : NotificationType = new NotificationType( "notificationDefault" );
		public static const WARN    : NotificationType = new NotificationType( "notificationWarn"    );
		public static const ERROR   : NotificationType = new NotificationType( "notificationError"   );
		public static const ASK     : NotificationType = new NotificationType( "notificationAsk"     );

		public var style:String;
		public function NotificationType( style:String )
		{
			this.style = style;
		}
	}
}