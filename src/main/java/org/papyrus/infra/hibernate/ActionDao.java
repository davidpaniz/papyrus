package org.papyrus.infra.hibernate;

import org.papyrus.domain.model.Action;
import org.papyrus.domain.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "actionRepository")
public class ActionDao implements ActionRepository {

	private final HibernateTemplate template;

	@Autowired
	public ActionDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Action save(Action action) {
		template.saveOrUpdate(action);
		return action;
	}
}
