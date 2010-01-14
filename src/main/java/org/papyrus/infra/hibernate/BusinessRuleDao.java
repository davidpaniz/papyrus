package org.papyrus.infra.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.Condition;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.action.Action;
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

	public List<BusinessRule> findCreateRules() {
		return findRule("onCreate");
	}

	public List<BusinessRule> findUpdateRules() {
		return findRule("onUpdate");
	}

	public List<BusinessRule> findDeleteRules() {
		return findRule("onDelete");
	}

	private List<BusinessRule> findRule(String field) {
		return getSession().createCriteria(BusinessRule.class)
				.add(Restrictions.eq("enabled", true))
				// .add(Restrictions.eq("type", type))
				.add(Restrictions.eq(field, true))
				.list();
	}

	public <T> T load(Class<T> type, Serializable id) {
		return (T) template.get(type, id);
	}

	public void unlock(Incident conditionComparable) {
		template.evict(conditionComparable);
	}

	public BusinessRule load(Long id) {
		BusinessRule load = (BusinessRule) getSession().load(BusinessRule.class, id);
		return load;
	}

	public void clearConditionsAndActionsOf(BusinessRule businessRule) {
		BusinessRule load = (BusinessRule) getSession().load(BusinessRule.class, businessRule.getId());
		List<Action> actions = load.getActions();
		List<Condition> conditions = load.getConditions();
		template.deleteAll(actions);
		template.deleteAll(conditions);
	}
}
