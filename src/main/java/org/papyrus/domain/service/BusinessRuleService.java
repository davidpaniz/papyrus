package org.papyrus.domain.service;

import org.papyrus.domain.exception.BusinessRuleException;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.model.ConditionType;

public interface BusinessRuleService {

	void executeCreateCondition(ConditionType type, ConditionComparable conditionComparable)
			throws BusinessRuleException;

	BusinessRule deleteBusinessRule(BusinessRule businessRule);

	BusinessRule saveBusinessRule(BusinessRule businessRule);
}
