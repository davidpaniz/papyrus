package org.papyrus.components.combobox
{
	import mx.collections.ArrayCollection;
	
	import org.papyrus.utils.ObservableMap;
	import org.papyrus.utils.Utils;
	
	public class DomainComboBox extends EntityComboBox
	{
		protected static var observableMap : ObservableMap   = new ObservableMap();
		protected static var observers     : ArrayCollection = new ArrayCollection();
		
		// method resource where combo will get the list
		public var serviceMethod:Function;

		// "Caching id" of the collection
		public var dataId:String;

		//Class of the combo
		public var entityClass:Class;

		public var isRequired:Boolean = false; 
		
		public var promptItem:String = "--";

		public function DomainComboBox()
		{
			addObserver( function( key:String ):void {
				if( dataId == key )
				{
					invalidateProperties();
				}
			} );
		}

		public static function refresh(key:String):void
		{
			if(observableMap.hasProperty(key))
				for each( var observer:Object in observers )
					observer( key );
		}
		
		public function addObserver( observer:Function ):void
		{
			observers.addItem( observer );
		}

		override protected function commitProperties():void
		{
			super.commitProperties();

			if( observableMap.hasProperty(dataId) && observableMap.getVal( dataId ) != null )
			{
				dataProvider = observableMap.getVal( dataId );
			}
			else
			{
 				callService();
 			}
		}
		
		protected function callService():void
		{
			serviceMethod( handler );
		}
		
		protected function handler( data:ArrayCollection ):void
		{
			var descriptionField:String = labelFunction == null ? labelField : "description";
			
			if(!isRequired)
			{
				data.addItemAt( Utils.createEmptyDomain( entityClass, promptItem, descriptionField ), 0 )
			}

			observableMap.put( dataId, data );
			refresh(dataId);
		}
		
		public static function getDataProvider(dataId:String):ArrayCollection
		{
			if( observableMap.hasProperty(dataId) && observableMap.getVal( dataId ) != null )
				return observableMap.getVal( dataId );
			
			return null;
		}
				
	}
}