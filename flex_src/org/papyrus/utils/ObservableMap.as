package org.papyrus.utils
{
	import mx.collections.ArrayCollection;
	
	public class ObservableMap extends Object
	{
		private var obj:Object = new Object(); 

		public function put( key:String, data:ArrayCollection ):void
		{
			obj[ key ] = data;
		}
		
		public function getVal( key:String ):ArrayCollection
		{
			return obj[ key ];
		}
		
		public function hasProperty(name:String):Boolean
		{
			return obj.hasOwnProperty(name);
		}
		
	}
}