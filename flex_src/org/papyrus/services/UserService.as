package org.papyrus.services
{
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.model.Model;
	import org.papyrus.model.User;

	public class UserService extends Service
	{
		public function UserService()
		{
			super( "userService" );
		}
		
		private var loginCallback:Function;
		public function login( user:User, remember:Boolean, callback:Function = null ):void
		{
			this.loginCallback = callback;
			service.login( user );

			saveSharedObject( remember ? user.email : "" );
		}
		public function loginResult( event:ResultEvent ):void
		{
			var loggedUser:User = User( event.result );
			Model.inst().user = loggedUser;
			
			loginCallback( loggedUser );
		}
		public function loginFault( event:FaultEvent ):void
		{
			if( loginCallback != null )
				loginCallback( false );
		}

		public function logoutUser():void
		{
			service.logoutUser();
		}

		public function logoutUserResult( event:ResultEvent ):void
		{
			Model.inst().user = null;
			//força refresh
			navigateToURL(  new URLRequest( "/papyrus" ), "_self" );

		}

		public function forgotPassword( email:String ):void
		{
			service.forgotPassword( email );
		}

		private function saveSharedObject( user:String ):void
		{
	    	getLSO().data.user = user;
		}

		public static function getEmailFromLSO():String
		{
			return getLSO().data.user;
		}
	}
}