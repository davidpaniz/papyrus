package org.papyrus.model
{
	

	[RemoteClass(alias="org.papyrus.domain.model.Detail")]
	[Bindable]
	public class Detail
	{
		public var id:Number;
		
		public var user:User;
		public var incident:Incident;
		public var date:Date;
		public var message:String;
	}
}