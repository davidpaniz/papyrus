package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.User")]
	public class User
	{
		public var id:Number;
		public var email:String;
		public var name:String;
		public var password:String;
		public var active:Boolean;
		
		public var role:String;
		
		public function User()
		{
		}

	}
}