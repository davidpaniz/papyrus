package org.papyrus.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="org.papyrus.domain.model.WorkOrder")]
	[Bindable]
	public class WorkOrder implements ConditionComparable
	{
		public var id:Number;
		public var description:String;
		
		public function WorkOrder()
		{
		}

		public function asDetail():ArrayCollection
		{
			var template:ArrayCollection = new ArrayCollection();
			return template;
			//FIXME 
		}
	}
}