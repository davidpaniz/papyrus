package org.papyrus.infra.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionType;
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
		List list = template.find("from BusinessRule");
		return list;

	}

	public BusinessRule delete(BusinessRule businessRule) {
		template.delete(businessRule);
		return businessRule;
	}

	public List<BusinessRule> findCreateRules(ConditionType type) {
		return findRule(type, "onCreate");
	}

	public List<BusinessRule> findUpdateRules(ConditionType type) {
		return findRule(type, "onUpdate");
	}

	private List<BusinessRule> findRule(ConditionType type, String field) {
		return template.getSessionFactory()
				.getCurrentSession()
				.createCriteria(BusinessRule.class)
				.add(Restrictions.eq("enabled", true))
				.add(Restrictions.eq("type", type))
				.add(Restrictions.eq(field, true))
				.list();
	}

	public <T> T load(Class<T> type, Serializable id) {
		return (T) template.get(type, id);
	}
}
