package org.papyrus.model
{
	import mx.collections.ArrayCollection;
	
	[RemoteClass(alias="org.papyrus.domain.model.Incident")]
	public class Incident implements ConditionComparable
	{
		public var id:Number;
		public var clientName:String;
		public var description:String;
		public var resolution:String;
		public var impact:Impact;
		public var urgency:Urgency;
		public var status:String;
		public var openedDate:Date;
		public var respondedDate:Date;
		public var dueDate:Date;
		public var workOrders:ArrayCollection;
		public var attachments:ArrayCollection;
		public var details:ArrayCollection;
		
		public function Incident()
		{
		}
	}
}