package org.papyrus.model.condition
{
	import org.papyrus.model.BusinessRule;
	import org.papyrus.screen.businessRules.condition.SpecificConditionSubForm;
	
	[RemoteClass(alias="org.papyrus.domain.model.condition.Condition")]
	[Bindable]
	public class Condition
	{
		public var id:Number;
		public var businessRule:BusinessRule;
		public var comparisonOperator:String;
		public var logicalOperator:String;
		
		public function get name():String {
			return "Condition";
		}
		
		public function getClass():Class {
			return Condition;
		}
		
		public function get subForm():SpecificConditionSubForm {
			return new SpecificConditionSubForm();
		}
		
		public function Condition()
		{
		}
	}
}