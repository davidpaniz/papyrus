package org.papyrus.components.combobox.enum
{
	import mx.collections.ArrayCollection;
	import mx.events.ListEvent;
	import mx.utils.ObjectUtil;
	
	import org.papyrus.components.combobox.AutoCompleteCombo;

	public class EnumComboBox extends AutoCompleteCombo
	{
		public var i18nName:String ;
		public var showAll:Boolean = false;
		
		protected var staticDataProvider:ArrayCollection = new ArrayCollection();
		protected function init():void{}
		
		protected function showI18NLabel(item:Object):String {
			return resourceManager.getString(i18nName, item.toString());
		}
		
		public function EnumComboBox() {
			super();
			init();
			labelFunction = showI18NLabel;
		}
		
		override public function set selectedItem( param:Object ):void
		{
			super.selectedItem = 1;
			var count:int = 0;
			for each( var obj:Object in collection )
			{
				if( obj && ObjectUtil.compare(obj, param) == 0 )
				{
					selectedIndex = count;
					super.selectedItem = obj;
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