package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Condition;
import org.papyrus.domain.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "conditionRepository")
public class ConditionDao implements ConditionRepository {

	private final HibernateTemplate template;

	@Autowired
	public ConditionDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Condition saveOrUpdate(Condition condition) {
		template.saveOrUpdate(condition);
		return condition;
	}

	public List<Condition> list() {
		List list = template.find("from Condition");
		return list;

	}

	public Condition delete(Condition condition) {
		template.delete(condition);
		return condition;
	}

}
