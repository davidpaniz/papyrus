package org.papyrus.components.containers
{
	
	import flash.display.DisplayObject;
	
	import mx.collections.ArrayCollection;
	import mx.containers.VBox;
	import mx.controls.Button;
	import mx.events.FlexEvent;
	import mx.events.ItemClickEvent;
	
	[Event(name="itemClick", type="mx.events.ItemClickEvent")]
	[Style(name="selectedColor", type="Number", inherit="no")]
	public class VBoxToggleManager extends VBox
	{
		public function VBoxToggleManager()
		{
			super();
		}
		
		private var buttonList:ArrayCollection;
		
	    override public function addChild(child:DisplayObject):DisplayObject
	    {
	        if(!buttonList)
	        	buttonList = new ArrayCollection();

			if(child is Button)
			{
				Button(child).addEventListener(FlexEvent.VALUE_COMMIT, valueComitHandler);
				buttonList.addItem(child);
			}
	        
	        return super.addChild(child)
	    }
	    
	    override public function removeChild(child:DisplayObject):DisplayObject
	    {
	    	if(buttonList.getItemIndex(child) > -1)
	    		buttonList.removeItemAt(buttonList.getItemIndex(child));
	    		
	    	return super.removeChild(child);	    	
	    }

	    override public function removeAllChildren():void
	    {
	    	super.removeAllChildren();
	    	
	    	buttonList = null;
	    }

		private var selectedButton:Button;
		private var oldColor:uint;
		
		private function valueComitHandler(event:FlexEvent):void
		{
			if(event.target == selectedButton)
			{
				selectedButton = null;
				event.target.setStyle("color", oldColor);
				dispatchEvent(new ItemClickEvent("itemClick", false, false, Button(event.target).label, -1));
			}
				
			if(Button(event.target).selected)
			{
				selectedButton = Button(event.target);
				oldColor = event.target.getStyle("color");
			}	
			else
			{
				return;
			}	
			
			var child:DisplayObject;
			for( var i:int = 0; i < buttonList.length; i++)
			{
				child = DisplayObject(buttonList.getItemAt(i));	
				
				if(event.target != child)
				{
					Button(child).selected = false;
				}
				else
				{
					dispatchEvent(new ItemClickEvent("itemClick", false, false, Button(child).label, i));
				}
				
				Button(child).setStyle("color", oldColor);
			}
			
			var selectedColor : Number = this.getStyle("selectedColor"); 
			
			if(selectedColor)
				selectedButton.setStyle("color", selectedColor);
			else
				selectedButton.setStyle("color", "0xFFFFFF");
		}
		
		private var _selectedIndex : int = -1;
		public function set selectedIndex(value:int):void
		{
			if(buttonList && value < buttonList.length && value > -1)
			{				
				_selectedIndex = value;
				
				Button(buttonList.getItemAt(value)).selected = true;
			}
			else
			{
				_selectedIndex = -1;
				
				selectedButton.selected = false;
				selectedButton = null;
			}
		}

		public function get selectedIndex():int
		{
			return _selectedIndex;
		}
		
	}

}