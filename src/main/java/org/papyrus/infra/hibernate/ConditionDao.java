package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.ActionCondition;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionType;
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

	public ActionCondition saveOrUpdate(ActionCondition condition) {
		template.saveOrUpdate(condition);
		return condition;
	}

	public List<ActionCondition> list() {
		List list = template.find("from Condition");
		return list;

	}

	public ActionCondition delete(ActionCondition condition) {
		template.delete(condition);
		return condition;
	}

	public List<BusinessRule> findCreateRules(ConditionType incident) {
		return template.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select br from BusinessRule br inner join br.conditions c where c.onCreate = true and c.type = :type")
				.setParameter("type", incident)
				.list();
	}
}
