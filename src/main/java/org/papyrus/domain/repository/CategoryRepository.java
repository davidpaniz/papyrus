package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Category;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist urgencies
 */
public interface CategoryRepository {
	/**
	 * 
	 * Save an category.
	 * 
	 * @return the category persisted with the id
	 * @param the
	 *            category that will be persisted
	 */
	Category saveOrUpdate(Category category);

	List<Category> list();

	/**
	 * @return the list only categories WITHOUT parent
	 */
	List<Category> listParentCategories();

	/**
	 * @param category
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	Category delete(Category category);

	/**
	 * @return the list only categories WITH parent
	 */
	List<Category> listChildCategories();

}
