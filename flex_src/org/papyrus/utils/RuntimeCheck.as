package org.papyrus.utils
{
    import flash.errors.IllegalOperationError;
    import flash.utils.describeType;
    import flash.utils.getQualifiedClassName;

	public class RuntimeCheck
	{
		public static function abstractClass(instance:Object, abstractClass:Class, abstractMethodList:Array = null):void {
			var instanceClassName:String = getQualifiedClassName(instance);
			if (instance.constructor === abstractClass) {
			    throw new IllegalOperationError("Abstract class '" + instanceClassName + "' must be subclassed and not instantiated.");
			} else {
			    var description:XML = describeType(instance);
			    //trace(description);
			    for each (var fnName:String in abstractMethodList) {
			        var overridenFlag:Boolean = false;
			        for (var pname:String in description.method) {
			            if(description.method.@name[pname].toString() == fnName) {
			                overridenFlag = (description.method.@declaredBy[pname].toString() === instanceClassName);
			            }
			        }
			        if (!overridenFlag) {
			            throw new IllegalOperationError("Abstract method '" + fnName + "' must be overridden and implemented in subclass.");
			        }
			    }
			}
		}
	}
}