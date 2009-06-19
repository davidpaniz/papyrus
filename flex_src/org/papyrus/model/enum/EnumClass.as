package org.papyrus.model.enum
{
	import flash.utils.Dictionary;

	public class EnumClass {
		private var _declaring:Boolean = false;
		private var _nextIndex:int = 0;

		public var valueMap:Dictionary = new Dictionary;
		public var values:Array = [];

		private var _elementClass:Class;
		public function EnumClass(elementClass:Class):void
		{
			_elementClass = elementClass;
		}

		internal function get declaring():Boolean { return _declaring; }
		internal function get nextIndex():int { return _nextIndex++; }

		public function declare(name:String):EnumBase
		{
			_declaring = true;
			const result:EnumBase = new _elementClass(name);
			valueMap[name] = result;
			values.push(result);
			_declaring = false;
			return result;
		}
	}
}