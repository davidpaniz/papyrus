package org.papyrus.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="org.papyrus.domain.model.MailNotification")]
	[Bindable]
	public class MailNotification implements ConditionComparable 
	{
		public var id:Number;
		
		public var subject:String;
		public var body:String;
		public var address:String;
		
		public function asDetail():ArrayCollection
		{
			var detail:ArrayCollection = new ArrayCollection();
			detail.addItem(TemplateValue.createTemplateValue("id", id.toString()));
			detail.addItem(TemplateValue.createTemplateValue("subject", subject));
			detail.addItem(TemplateValue.createTemplateValue("body", body));
			detail.addItem(TemplateValue.createTemplateValue("address", address));
			return detail;
		}
	}
}