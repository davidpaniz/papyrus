package org.papyrus.domain.service;

import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionType;

public interface BusinessRuleService {

	void executeCreateCondition(ConditionType incident);

	BusinessRule deleteBusinessRule(BusinessRule businessRule);

	BusinessRule saveBusinessRule(BusinessRule businessRule);
}
