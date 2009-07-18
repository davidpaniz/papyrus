package org.papyrus.infra.hibernate;

import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.repository.ConditionComparableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "conditionComparableRepository")
public class ConditionComparableDao implements ConditionComparableRepository {

	private final HibernateTemplate template;

	@Autowired
	public ConditionComparableDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public ConditionComparable save(ConditionComparable conditionComparable) {
		template.saveOrUpdate(conditionComparable);
		return conditionComparable;
	}

}
