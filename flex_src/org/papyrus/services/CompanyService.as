package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.combobox.CompanyComboBox;
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Company;

	public class CompanyService extends Service
	{
		public function CompanyService()
		{
			super( "companyService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listCompany( callback:Function ):void
		{
			listCallback = callback;
			service.listCompany( );
		}
 		public function listCompanyResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function saveCompany( company:Company, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.saveCompany( company );
		}
		
		public function saveCompanyResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as Company);
				
			CompanyComboBox.reset();
		}
		
		public function saveCompanyFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		private var deleteCallback:Function;
		public function deleteCompany( company:Company, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteCompany( company );
		}

		public function deleteCompanyResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as Company);
				
			CompanyComboBox.reset();
		}
	}
}