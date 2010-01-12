package org.papyrus.screen.businessRules.action
{
	import mx.containers.HBox;
	
	import org.papyrus.model.action.Action;
	import org.papyrus.utils.RuntimeCheck;

	public class SpecificActionSubForm extends HBox
	{
		[Bindable]
		public var isTemplate:Boolean = false;
		
		public function get action():Action {
			return null;
		}
		
		public function SpecificActionSubForm() {
//			RuntimeCheck.abstractClass(this, SpecificActionSubForm, ["action"]);
		}
		
	}
}