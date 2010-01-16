package org.papyrus.screen.businessRules.condition
{
	import mx.containers.HBox;
	
	import org.papyrus.model.condition.Condition;

	public class SpecificConditionSubForm extends HBox
	{
		public function get condition():Condition {
			return null;
		}
		
		public function set condition(condition:Condition):void {
		}
		
		public function SpecificConditionSubForm() {
//			RuntimeCheck.abstractClass(this, SpecificConditionSubForm, ["action"]);
		}
		
	}
}