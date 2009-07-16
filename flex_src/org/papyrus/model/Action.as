package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.Action")]
	[Bindable]
	public class Action
	{
		public var id:Number;
		
		public var businessRule:BusinessRule;
		public var method:String;
		public var type:String;
		public var detail:ConditionComparable;
	}
}