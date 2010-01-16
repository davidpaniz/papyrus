package org.papyrus.model.condition
{
	import org.papyrus.screen.businessRules.condition.SpecificConditionSubForm;
	import org.papyrus.screen.businessRules.condition.StatusConditionSubForm;
	

	[RemoteClass(alias="org.papyrus.domain.model.condition.StatusCondition")]
	[Bindable]
	public class StatusCondition extends Condition
	{
		public var status:String;
		
		public function StatusCondition()
		{
		}
		
		
		public override function get name():String{
			return "statusCondition"
		}
		
		public override function get subForm():SpecificConditionSubForm {
			return new StatusConditionSubForm();
		}
		
		public override function getClass():Class{
			return StatusCondition;
		}
	}
}