package org.papyrus.model.condition
{
	import org.papyrus.model.BusinessRule;
	
	[RemoteClass(alias="org.papyrus.domain.model.condition.Condition")]
	[Bindable]
	public class Condition
	{
		public var id:Number;
		public var comparisonOperator:String;
		public var logicalOperator:String;
		public var businessRule:BusinessRule;
		
		public function Condition()
		{
		}
	}
}