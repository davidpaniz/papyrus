package org.papyrus.model.action
{
	import org.papyrus.model.BusinessRule;
	import org.papyrus.screen.businessRules.action.SpecificActionSubForm;

	[RemoteClass(alias="org.papyrus.domain.model.action.Action")]
	[Bindable]
	public class Action
	{
		public var id:Number;
		
		public var businessRule:BusinessRule;
		
		public function get name():String{
			return "Action";
		}
		
		public function getClass():Class{
			return Action;
		}
		
		public function get subForm():SpecificActionSubForm{
			return new SpecificActionSubForm();
		}
		
		
		public function Action() {
		}
	}
}