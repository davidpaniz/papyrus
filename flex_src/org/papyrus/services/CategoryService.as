package org.papyrus.services
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.papyrus.components.combobox.CategoryChildComboBox;
	import org.papyrus.components.combobox.CategoryRootComboBox;
	import org.papyrus.components.notification.NotificatorManager;
	import org.papyrus.model.Category;

	public class CategoryService extends Service
	{
		public function CategoryService(callBackFunction:Function)
		{
			super( "categoryService", callBackFunction );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
 		public function listCategory():void
		{
			service.listCategory();
		}
 		public function listCategoryResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		public function listParentCategories():void
		{
			service.listParentCategories();
		}
 		public function listParentCategoriesResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		public function listChildCategories():void
		{
			service.listChildCategories();
		}
 		public function listChildCategoriesResult( event:ResultEvent ):void
		{
			callBackFunction( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		public function saveCategory( category:Category ):void
		{
			service.saveCategory( category );
		}
		
		public function saveCategoryResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Category);
				
			updateCombobox();	
		}
		
		public function saveCategoryFault( event:FaultEvent ):void
		{
			if(event.fault.faultString.indexOf("DataAlreadyExistsException"))
				NotificatorManager.error("Já existe uma palavra com essa descrição");
		}


		/*************************************
		 * DELETE 
		 * ***********************************/
		
		public function deleteCategory( category:Category ):void
		{
			service.deleteCategory( category );
		}

		public function deleteCategoryResult( event:ResultEvent ):void
		{
			callBackFunction(event.result as Category);
				
			updateCombobox();
		}
		
		private function updateCombobox():void {
			CategoryRootComboBox.reset();
			CategoryChildComboBox.reset();
		}
	}
}