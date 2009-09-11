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
		public function CategoryService()
		{
			super( "categoryService" );
		}
		
		/*************************************
		 * GET
		 * ***********************************/
		
		private var listCallback:Function;
 		public function listCategory( callback:Function ):void
		{
			listCallback = callback;
			service.listCategory( );
		}
 		public function listCategoryResult( event:ResultEvent ):void
		{
			listCallback( event.result as ArrayCollection);
		}
		
		private var listParentCallback:Function;
		public function listParentCategories( callback:Function ):void
		{
			listParentCallback = callback;
			service.listParentCategories( );
		}
 		public function listParentCategoriesResult( event:ResultEvent ):void
		{
			listParentCallback( event.result as ArrayCollection);
		}
		
		private var listChildCallback:Function;
		public function listChildCategories( callback:Function ):void
		{
			listParentCallback = callback;
			service.listChildCategories( );
		}
 		public function listChildCategoriesResult( event:ResultEvent ):void
		{
			listChildCallback( event.result as ArrayCollection);
		}
		
		/*************************************
		 * SAVE
		 * ***********************************/
		
		private var saveCallback:Function;
		public function saveCategory( category:Category, callbackFunction:Function ):void
		{
			this.saveCallback = callbackFunction;
			service.saveCategory( category );
		}
		
		public function saveCategoryResult( event:ResultEvent ):void
		{
			if(saveCallback != null)
				saveCallback(event.result as Category);
				
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
		
		private var deleteCallback:Function;
		public function deleteCategory( category:Category, callbackFunction:Function ):void
		{
			this.deleteCallback = callbackFunction;
			service.deleteCategory( category );
		}

		public function deleteCategoryResult( event:ResultEvent ):void
		{
			if(deleteCallback != null)
				deleteCallback(event.result as Category);
				
			updateCombobox();
		}
		
		private function updateCombobox():void {
			CategoryRootComboBox.reset();
			CategoryChildComboBox.reset();
		}
	}
}