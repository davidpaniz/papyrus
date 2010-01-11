package org.papyrus.infra.hibernate;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.repository.ConditionComparableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "conditionComparableRepository")
public class ConditionComparableDao implements ConditionComparableRepository {

	private final HibernateTemplate template;

	@Autowired
	private ConditionComparableDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Incident saveTemplate(Incident conditionComparable) {
		conditionComparable.asTemplate();
		template.save(conditionComparable);
		return conditionComparable;
	}

	public void activeTemplate(Incident conditionComparable) {
		Incident loadedCondition = (Incident) template.load(conditionComparable.getClass(), conditionComparable.getId());
		loadedCondition.activeTemplate();
		template.update(loadedCondition);
	}

	public Incident load(Incident detail) {
		return (Incident) template.load(detail.getClass(), detail.getId());
	}

}
