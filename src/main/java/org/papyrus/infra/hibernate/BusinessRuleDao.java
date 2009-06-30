package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.BusinessRule;
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
}
