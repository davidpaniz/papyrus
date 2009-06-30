package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Action;
import org.papyrus.domain.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "actionRepository")
public class ActionDao implements ActionRepository {

	private final HibernateTemplate template;

	@Autowired
	public ActionDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Action saveOrUpdate(Action action) {
		template.saveOrUpdate(action);
		return action;
	}

	public List<Action> list() {
		List list = template.find("from Action");
		return list;

	}

	public Action delete(Action action) {
		template.delete(action);
		return action;
	}
}
