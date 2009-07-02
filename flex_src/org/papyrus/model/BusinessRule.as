package org.papyrus.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="org.papyrus.domain.model.BusinessRule")]
	[Bindable]
	public class BusinessRule
	{
		public var id:Number;
		public var description:String;
		public var name:String;
		public var enabled:Boolean;
		public var conditions:ArrayCollection;
		public var actions:ArrayCollection;
		
	}
}