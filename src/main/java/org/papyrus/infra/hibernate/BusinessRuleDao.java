package org.papyrus.infra.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.classic.Session;
import org.hibernate.collection.AbstractPersistentCollection;
import org.hibernate.criterion.Restrictions;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.BusinessRuleType;
import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.repository.BusinessRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "businessRuleRepository")
public class BusinessRuleDao implements BusinessRuleRepository {

	private final HibernateTemplate template;

	@Autowired
	public BusinessRuleDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public BusinessRule saveOrUpdate(BusinessRule businessRule) {
		template.saveOrUpdate(businessRule);
		return businessRule;
	}

	public List<BusinessRule> list() {
		List<BusinessRule> list = getSession().createQuery("from BusinessRule")
				.list();
		for (BusinessRule businessRule : list) {
			businessRule.setConditions(null);
			businessRule.setActions(null);
		}
		return list;

	}

	private Session getSession() {
		return template.getSessionFactory()
				.getCurrentSession();
	}

	public BusinessRule delete(BusinessRule businessRule) {
		template.delete(businessRule);
		return businessRule;
	}

	public List<BusinessRule> findCreateRules(BusinessRuleType type) {
		return findRule(type, "onCreate");
	}

	public List<BusinessRule> findUpdateRules(BusinessRuleType type) {
		return findRule(type, "onUpdate");
	}

	public List<BusinessRule> findDeleteRules(BusinessRuleType type) {
		return findRule(type, "onDelete");
	}

	private List<BusinessRule> findRule(BusinessRuleType type, String field) {
		return getSession().createCriteria(BusinessRule.class)
				.add(Restrictions.eq("enabled", true))
				.add(Restrictions.eq("type", type))
				.add(Restrictions.eq(field, true))
				.list();
	}

	public <T> T load(Class<T> type, Serializable id) {
		return (T) template.get(type, id);
	}

	public void unlock(ConditionComparable conditionComparable) {
		template.evict(conditionComparable);
	}

	public BusinessRule load(Long id) {
		BusinessRule load = (BusinessRule) getSession().load(BusinessRule.class, id);
		loadCollection(load.getActions());
		loadCollection(load.getConditions());
		return load;
	}

	private void loadCollection(Collection collection) {
		if (collection instanceof AbstractPersistentCollection) {
			((AbstractPersistentCollection) collection).forceInitialization();
		}
	}
}
