package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.combobox.StaffComboBox;
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Staff;

	public class StaffService extends Service
	{
		public function StaffService()
		{
			super( "staffService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listStaff( callback:Function ):void
		{
			listCallback = callback;
			service.listStaff( );
		}
 		public function listStaffResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function saveStaff( staff:Staff, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.saveStaff( staff );
		}
		
		public function saveStaffResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as Staff);
				
			StaffComboBox.reset();
		}
		
		public function saveStaffFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		private var deleteCallback:Function;
		public function deleteStaff( staff:Staff, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteStaff( staff );
		}

		public function deleteStaffResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as Staff);
				
			StaffComboBox.reset();
		}
	}
}