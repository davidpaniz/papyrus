package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.Incident;

public interface BusinessRuleService {

	void executeCreateCondition(Incident incident);

	void executeUpdateCondition(Incident incident);

	void executeDeleteCondition(Incident incident);

	BusinessRule deleteBusinessRule(BusinessRule businessRule);

	BusinessRule saveBusinessRule(BusinessRule businessRule);

	BusinessRule loadBusinessRule(Long id);

	List<BusinessRule> listBusinessRule();

}
