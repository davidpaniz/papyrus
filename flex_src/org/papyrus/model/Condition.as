package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.Condition")]
	[Bindable]
	public class Condition
	{
		public var id:Number;

		public var expression1:String;
		public var expression2:String;

		public var comparisonOperator:String;
		public var logicalOperator:String;
		
		public var businessRule:BusinessRule;
	}
}