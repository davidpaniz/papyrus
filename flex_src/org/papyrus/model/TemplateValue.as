package org.papyrus.model
{
	

	[RemoteClass(alias="org.papyrus.domain.model.TemplateValue")]
	[Bindable]
	public class TemplateValue
	{
		public var id:Number;
		
		public var field:String;
		public var value:String;
		
		public function TemplateValue()
		{
		}
		
		public static function createTemplateValue(field:String, value:String):TemplateValue
		{
			var t:TemplateValue = new TemplateValue();
			t.field = field;
			t.value = value;
			return t;
		}
		
	}
}