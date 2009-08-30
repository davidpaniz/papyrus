/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Category;
import org.papyrus.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "categoryService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CategoryServiceImp implements CategoryService {
	private final CategoryRepository repository;

	@Autowired
	public CategoryServiceImp(CategoryRepository repository) {
		this.repository = repository;
	}

	public List<Category> listCategory() throws Exception {
		return repository.list();
	}

	public List<Category> listParentCategories() throws Exception {
		return repository.listParentCategories();
	}

	public Category deleteCategory(Category category) throws Exception {
		return repository.delete(category);
	}

	public Category saveCategory(Category category) throws Exception {
		Category parent = category.getParent();
		if (parent != null && parent.getId() == 0) {
			category.setParent(null);
		}
		return repository.saveOrUpdate(category);
	}

}
