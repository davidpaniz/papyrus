package org.papyrus.model
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="org.papyrus.domain.model.Incident")]
	public class Incident implements ConditionComparable
	{
		public var id:Number;
		public var requester:User;
		public var responsable:User;
		public var description:String;
		public var resolution:String;
		public var priority:Priority;
		public var status:String;
		public var openedDate:Date;
		public var respondedDate:Date;
		public var dueDate:Date;
		public var attachments:ArrayCollection = new ArrayCollection();
		public var details:ArrayCollection = new ArrayCollection();
		public var category:Category;
		
		public function Incident()
		{
		}
	}
}