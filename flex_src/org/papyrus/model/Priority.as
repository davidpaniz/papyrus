package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.Priority")]
	[Bindable]
	public class Priority
	{
		public var id:Number;
		public var description:String;
		public var impact:Impact;
		public var urgency:Urgency;
	}
}