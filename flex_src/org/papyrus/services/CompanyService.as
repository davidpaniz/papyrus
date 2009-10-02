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
		public function CompanyService(callBackFunction:Function)
		{
			super( "companyService", callBackFunction );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listCompany( ):void
		{
			service.listCompany( );
		}
 		public function listCompanyResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function saveCompany( company:Company ):void
		{
			service.saveCompany( company );
		}
		
		public function saveCompanyResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Company);
				
			CompanyComboBox.reset();
		}
		
		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deleteCompany( company:Company ):void
		{
			service.deleteCompany( company );
		}

		public function deleteCompanyResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Company);
				
			CompanyComboBox.reset();
		}
	}
}