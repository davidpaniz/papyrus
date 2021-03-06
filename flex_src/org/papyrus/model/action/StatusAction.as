package org.papyrus.model.action
{
	import org.papyrus.screen.businessRules.action.SpecificActionSubForm;
	import org.papyrus.screen.businessRules.action.StatusActionSubForm;

	[RemoteClass(alias="org.papyrus.domain.model.action.StatusAction")]
	[Bindable]
	public class StatusAction extends Action
	{
		public var status:String;
		
		public override function get name():String{
			return "statusAction"
		}
		
		public override function get subForm():SpecificActionSubForm{
			return new StatusActionSubForm();
		}
		
		public override function getClass():Class{
			return StatusAction;
		}
	}
}