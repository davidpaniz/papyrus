package org.papyrus.screen.businessRules.action
{
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	
	import org.papyrus.utils.RuntimeCheck;

	public class SpecificActionSubForm extends HBox
	{
		[Bindable]
		public var isTemplate:Boolean = false;
		
		public function asDetail():ArrayCollection {
			return null;
		}
		
		public function SpecificActionSubForm() {
			RuntimeCheck.abstractClass(this, SpecificActionSubForm, ["asDetail"]);
		}
		
	}
}