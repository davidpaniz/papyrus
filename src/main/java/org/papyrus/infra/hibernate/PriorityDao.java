package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Priority;
import org.papyrus.domain.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "priorityRepository")
public class PriorityDao implements PriorityRepository {

	private final HibernateTemplate template;

	@Autowired
	public PriorityDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Priority saveOrUpdate(Priority priority) {
		template.saveOrUpdate(priority);
		return priority;
	}

	public List<Priority> list() {
		List list = template.find("from Priority");
		return list;

	}

	public Priority delete(Priority priority) {
		template.delete(priority);
		return priority;
	}
}
