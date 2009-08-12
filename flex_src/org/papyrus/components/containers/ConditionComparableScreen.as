package org.papyrus.components.containers
{
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.controls.Alert;
	
	import org.papyrus.utils.RuntimeCheck;

	public class ConditionComparableScreen extends HBox
	{
		[Bindable]
		public var isTemplate:Boolean = false;
		
		
		public var closeFunction:Function;
		public var onOk:Function;
		public var onCancel:Function;
		
		public function asDetail():ArrayCollection {
			return null;
		}
		
		public function ConditionComparableScreen() {
			RuntimeCheck.abstractClass(this, ConditionComparableScreen, ["asDetail"]);
		}
		
	}
}