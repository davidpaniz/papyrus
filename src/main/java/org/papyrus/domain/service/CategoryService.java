/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Category;

/**
 * @author davidpaniz
 * 
 */
public interface CategoryService {
	Category deleteCategory(Category category) throws Exception;

	Category saveCategory(Category category) throws Exception;

	List<Category> listCategory() throws Exception;

	List<Category> listParentCategories() throws Exception;

	List<Category> listChildCategories() throws Exception;
}
