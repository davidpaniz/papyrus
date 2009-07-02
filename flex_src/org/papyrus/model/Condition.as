package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.Condition")]
	[Bindable]
	public class Condition
	{
		public var id:Number;
		
		public var onCreate:Boolean;
		public var onUpdate:Boolean;
		public var onDelete:Boolean;
		
		public var type:String;
		
		public var businessRule:BusinessRule;
	}
}