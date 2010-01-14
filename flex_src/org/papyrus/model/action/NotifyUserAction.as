package org.papyrus.model.action
{
	import org.papyrus.screen.businessRules.action.NotifyUserActionSubForm;
	import org.papyrus.screen.businessRules.action.SpecificActionSubForm;

	[RemoteClass(alias="org.papyrus.domain.model.action.NotifyUserAction")]
	[Bindable]
	public class NotifyUserAction extends Action
	{
		
		public var userAction:UserAction;
		public var subject:String;
		public var body:String;
		
		public override function get name():String{
			return "notifyUserAction"
		}
		
		public override function get subForm():SpecificActionSubForm{
			return new NotifyUserActionSubForm();
		}
		
		public override function getClass():Class{
			return NotifyUserAction;
		}

	}
}