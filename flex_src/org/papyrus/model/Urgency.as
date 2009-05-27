package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.Urgency")]
	[Bindable]
	public class Urgency
	{
		public var id:Number;
		public var name:String;
		public var description:String;
		public var position:Number;

	}
}