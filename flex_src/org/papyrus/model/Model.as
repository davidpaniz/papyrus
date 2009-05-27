package org.papyrus.model
{
	//import org.papyrus.states.State;

	[Bindable]
	public class Model
	{
		public var user                      : User;
		//public var currentModule             : State = State.HOME;
		
		private static var instance:Model = new Model();
		private static var locked:Boolean = false;
		{
			locked = true;
		}
		public function Model()
		{
			if( locked == true )
				throw new Error( "Please use the inst() method" );
		}

		public static function inst():Model
		{
			return instance;
		}
	}
}