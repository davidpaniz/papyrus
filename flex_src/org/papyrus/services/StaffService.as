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
		public function StaffService(callback:Function)
		{
			super( "staffService", callback );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listStaff():void
		{
			service.listStaff();
		}
 		public function listStaffResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
 		public function listAllIncidents(status:String, initialDate:Date, endDate:Date):void
		{
			service.listAllIncidents(status, initialDate, endDate );
		}
 		public function listAllIncidentsResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
 		public function listIncidentsAssignedToMe(status:String, initialDate:Date, endDate:Date):void
		{
			service.listIncidentsAssignedToMe(status, initialDate, endDate );
		}
 		public function listIncidentsAssignedToMeResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function saveStaff( staff:Staff):void
		{
			service.saveStaff( staff );
		}
		
		public function saveStaffResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Staff);
				
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
		
		public function deleteStaff( staff:Staff ):void
		{
			service.deleteStaff( staff );
		}

		public function deleteStaffResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Staff);
				
			StaffComboBox.reset();
		}
	}
}