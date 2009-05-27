package org.papyrus.services
{
	import flash.net.SharedObject;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.AbstractEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.InvokeEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Model;
	import org.papyrus.utils.GenericLoader;
	

	public class Service
	{
		private static const SUFIX_RESULT:String = "Result";
		private static const SUFIX_FAULT:String = "Fault";

		protected var service:RemoteObject;
		protected var services:Object /*map de RemoteObject*/;

		protected var model:Model = Model.inst();


		public static function getLSO():SharedObject
		{
			return SharedObject.getLocal( "papyrus" );
		}


		function Service( source:String = null, sources:ArrayCollection = null )
		{
			if( source != null )
			{
				service = createRemoteObject( source );
			}
			else
			{
				services = new Object();
				for each( var source:String in sources )
					services[ source ] = createRemoteObject( source );
			}
		}
		private function createRemoteObject( source:String ):RemoteObject
		{
			var ro:RemoteObject = new RemoteObject();
			ro.destination = source;
			ro.addEventListener( InvokeEvent.INVOKE, invoke );
			ro.addEventListener( ResultEvent.RESULT, result );
			ro.addEventListener( FaultEvent.FAULT, fault );

			return ro;
		}

		private function invoke( event:InvokeEvent ):void
		{
			before();
		}

		private function result( event:ResultEvent ):void
		{
			resultOrFault( event, defaultResultHandler, SUFIX_RESULT );
		}

		private function fault( event:FaultEvent ):void
		{
			if( event.fault.faultString.indexOf( "org.papyrus.domain.exception.UserNotLoggedIn" ) == 0 )
			{
				NotificatorManager. notificationWithFunction("Sua sessão expirou! Por favor, faça login novamente.", new UserService().logoutUser );
			}
			else if(event.fault.faultString.indexOf( "java.lang.reflect.UndeclaredThrowableException" ) == 0)
			{
				NotificatorManager.notificationWithFunction("Sua sessão expirou! Por favor, faça login novamente.", new UserService().logoutUser );
			}
			else
				resultOrFault( event, defaultFaultHandler, SUFIX_FAULT );
		}

		private function resultOrFault( event:AbstractEvent, defaultHandler:Function, sufix:String ):void
		{
			removeEventListeners();
			try
			{
				var method:String = getMethod( event.token ) + sufix; /// xxxResult ou xxxFault
				if( this.hasOwnProperty( method ) )
					execute( method, event );
				else
					defaultHandler( event )
			}
			catch( e:Error )
			{
				throw e;
			}
			finally
			{
				after();
			}

			delete this;
		}

		private function removeEventListeners():void
		{
			var wrapper:Function = function( ro:RemoteObject ):void {
				ro.removeEventListener( InvokeEvent.INVOKE, invoke );
				ro.removeEventListener( ResultEvent.RESULT, result );
				ro.removeEventListener( FaultEvent.FAULT, fault );
			}
			if( service != null )
			{
				wrapper( service )
			}
			else
			{
				for each( var ro:RemoteObject in services )
					wrapper( ro );
			}
		}

		private function execute( method:String, event:AbstractEvent ):void
		{
			this[ method ]( event );
		}

		private function getMethod( token:Object ):String
		{
			// nome do método executado no RemoteObject
			return token ? AsyncToken( token ).message[ "operation" ] : "";
		}

		protected function before():void
		{
			GenericLoader.show();
		}

		protected function after():void
		{
			GenericLoader.hide();
		}

		protected function defaultResultHandler( event:ResultEvent ):void
		{
		}

		protected function defaultFaultHandler( event:FaultEvent ):void
		{
			var method:String = getMethod( event.token );
			trace( event.fault.getStackTrace() );
			Alert.show( event.fault.getStackTrace(), "Error in method " + this + "." + method + "()" );
		}
	}
}