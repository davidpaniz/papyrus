package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.Category")]
	[Bindable]
	public class Category
	{
		public var id:Number;
		public var name:String;
		public var parent:Category;
	}
}