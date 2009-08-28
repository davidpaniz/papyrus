package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.Client")]
	[Bindable]
	public class Client
	{
		public var id:Number;
		public var name:String;
		public var email:String;
		public var company:Company;
	}
}