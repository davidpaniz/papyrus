package org.papyrus.components.combobox
{
	import mx.collections.ArrayCollection;
	import mx.controls.ComboBox;
	import mx.events.ListEvent;
	
	public class EntityComboBox extends ComboBox
	{
		protected var resourceFunction:Function;
		
		override public function set selectedItem( entity:Object ):void
		{
			super.selectedItem = 1;
			var count:int = 0;
			for each( var obj:Object in collection )
			{
				if( obj && obj.id && entity && entity.id && obj.id == entity.id )
				{
					selectedIndex = count;
					dispatchEvent( new ListEvent( ListEvent.CHANGE ) );
					return;
				}
				count++;
			}

			// se chegou aqui é porque não achou o item
			super.selectedIndex = 0;
		}
		
		public function init():void {
			resourceFunction(configDataProvider);
		}
		
		private function configDataProvider(dataProvider:ArrayCollection):void {
			super.dataProvider = dataProvider;
		}
	}
}