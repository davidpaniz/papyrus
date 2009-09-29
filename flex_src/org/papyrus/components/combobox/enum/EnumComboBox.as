package org.papyrus.components.combobox.enum
{
	import mx.collections.ArrayCollection;
	import mx.events.ListEvent;
	
	import org.papyrus.components.combobox.AutoCompleteCombo;

	public class EnumComboBox extends AutoCompleteCombo
	{
		public var i18nName:String ;
		protected var staticDataProvider:ArrayCollection = new ArrayCollection();	
		
		protected function showI18NLabel(item:Object):String {
			return resourceManager.getString(i18nName, item.toString());
		}
		
		public function EnumComboBox() {
			super();
		}
		
		override public function set selectedItem( param:Object ):void
		{
			super.selectedItem = 1;
			var count:int = 0;
			for each( var obj:Object in staticDataProvider )
			{
				if( obj && obj == param )
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