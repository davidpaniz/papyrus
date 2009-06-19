package org.papyrus.model.enum
{
	import flash.events.EventDispatcher;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	public class EnumBase extends EventDispatcher implements IExternalizable
	{
		public namespace as3_lang;

		private var _ordinal:int;
		[Transient]
		private var _name:String;
		[Transient]
		private var _c:EnumClass;

		public function EnumBase(C:EnumClass, name:String = null):void
		{
			_c = C;

			if (!name) {
				_ordinal = -1;
				return;
			}
			if ( !C.declaring )
				throw Error("Illegal attempt to create enum value");

			_ordinal = C.nextIndex;
			_name = name;
		}

		public function get ordinal():int { return _ordinal; }
		public function get name():String { return _name; }

		final public function writeExternal(output:IDataOutput):void
		{
			output.writeInt(_ordinal);
		}

		public function readExternal(input:IDataInput):void
		{
			_ordinal = input.readInt();
			_name = _c.values[_ordinal].name;
		}

		as3_lang function intern():EnumBase
		{
			return _c.values[_ordinal];
		}

		public function equals(o:Object):Boolean
		{
			if ( !(o is EnumBase) )
				return false;
			if ( this === o)
				return true;

			const other:EnumBase = EnumBase(o);
			return other._c === _c && other._ordinal === _ordinal;
		}

		public function valueOf():Number { return _ordinal; }
		override public function toString():String { return _name; }

		as3_lang static function enumOf(entryClass:Class):EnumClass
		{
			return new EnumClass(entryClass);
		}
	}
}