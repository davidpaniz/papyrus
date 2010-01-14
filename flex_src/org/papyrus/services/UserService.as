package org.papyrus.services
{
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.combobox.ClientComboBox;
	import org.papyrus.components.combobox.StaffComboBox;
	import org.papyrus.model.Model;
	import org.papyrus.model.User;

	public class UserService extends Service
	{
		public function UserService(callback:Function)
		{
			super( "userService", callback );
		}
		
		public function login( user:User, remember:Boolean):void
		{
			service.login( user );

			saveSharedObject( remember ? user.email : "" );
		}
		public function loginResult( event:ResultEvent ):void
		{
			var loggedUser:User = User( event.result );
			Model.inst().user = loggedUser;
			
			callBackFunction( loggedUser );
		}
		public function loginFault( event:FaultEvent ):void
		{
			callBackFunction( false );
		}

		public function logoutUser():void
		{
			service.logoutUser();
		}

		public function logoutUserResult( event:ResultEvent ):void
		{
			Model.inst().user = null;
			//force refresh
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
		
		public function listMyIncidents(status:String, initialDate:Date, endDate:Date):void
		{
			service.listMyIncidents( status, initialDate, endDate );
		}
		
		public function listMyIncidentsResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		public function listAllIncidents(status:String, initialDate:Date, endDate:Date):void
		{
			service.listAllIncidents(status, initialDate, endDate );
		}
		public function listAllIncidentsResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		public function listIncidentsAssignedToMe(status:String, initialDate:Date, endDate:Date):void
		{
			service.listIncidentsAssignedToMe(status, initialDate, endDate );
		}
		public function listIncidentsAssignedToMeResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}		
		
		public function listAllStaffs():void
		{
			service.listAllStaffs( );
		}
		public function listAllStaffsResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}		
		
		public function listAllClients():void
		{
			service.listAllClients( );
		}
		public function listAllClientsResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}		
		
		public function saveUser(user:User):void
		{
			service.saveUser( user );
		}
		public function saveUserResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as User);
			resetCombos();
		}		
		
		public function deleteUser(user:User):void
		{
			service.deleteUser( user );
		}
		public function deleteUserResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as User);
			resetCombos();
		}
		
		private function resetCombos():void {
			ClientComboBox.reset();
			StaffComboBox.reset();
		}
	}
}