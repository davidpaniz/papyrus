package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Category;
import org.papyrus.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "categoryRepository")
public class CategoryDao implements CategoryRepository {

	private final HibernateTemplate template;

	@Autowired
	public CategoryDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Category saveOrUpdate(Category category) {
		template.saveOrUpdate(category);
		return category;
	}

	public List<Category> list() {
		List list = template.find("from Category");
		return list;
	}

	public List<Category> listParentCategories() {
		List list = template.find("from Category where parent is null");
		return list;
	}

	public List<Category> listChildCategories() {
		List list = template.find("from Category where parent is not null");

		return list;
	}

	public Category delete(Category category) {
		template.delete(category);
		return category;
	}
}
