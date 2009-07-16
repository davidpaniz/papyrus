package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.WorkOrder")]
	public class WorkOrder implements ConditionComparable
	{
		public var id:Number;
		public var description:String;
		
		public function WorkOrder()
		{
		}

	}
}