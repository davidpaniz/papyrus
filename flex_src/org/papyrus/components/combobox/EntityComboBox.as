package org.papyrus.components.combobox
{
	import mx.events.ListEvent;

	public class EntityComboBox extends AutoCompleteCombo
	{
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

			// It was not found
			super.selectedIndex = 0;
		}
	}
}