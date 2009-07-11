package org.papyrus.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="org.papyrus.domain.model.BusinessRule")]
	[Bindable]
	public class BusinessRule
	{
		public var id:Number;
		
		public var enabled:Boolean;
		public var description:String;
		public var name:String;
		
		public var conditions:ArrayCollection;
		public var actions:ArrayCollection;
		
		public var onCreate:Boolean;
		public var onUpdate:Boolean;
		public var onDelete:Boolean;
		
		public var type:String
		
		public function BusinessRule() {
			
		}
	}
}