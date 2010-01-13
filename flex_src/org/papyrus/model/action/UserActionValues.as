package org.papyrus.model.action
{
	import mx.resources.ResourceManager;

	[RemoteClass(alias="org.papyrus.domain.model.action.UserActionValues")]
	[Bindable]
	public class UserActionValues implements UserAction
	{
		public var id:Number;
		public var template:String;
		
		public function UserActionValues(){}
		
		public function get name():String{
			return template;
		}
	}
}