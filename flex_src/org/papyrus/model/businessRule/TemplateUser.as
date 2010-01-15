package org.papyrus.model.businessRule
{
	import mx.resources.IResourceManager;
	
	import org.papyrus.model.User;

	[RemoteClass(alias="org.papyrus.domain.model.businessRule.TemplateUser")]
	[Bindable]
	public class TemplateUser
	{
		public var realUser:User;
		public var template:String;
		
		public function TemplateUser(){}
		
		public function getName(resourceManager:IResourceManager):String{
			if(realUser){
				return realUser.name;
			} else {
				return resourceManager.getString("businessRule", template);
			}
		}
		
		
		public static function createByEnum(enum:String):TemplateUser {
			var templateUser:TemplateUser = new TemplateUser();
			templateUser.template = enum;
			return templateUser;
		}
		
		public static function createByUser(user:User):TemplateUser {
			var templateUser:TemplateUser = new TemplateUser();
			templateUser.realUser = user;
			return templateUser;
		}
		
	}
}