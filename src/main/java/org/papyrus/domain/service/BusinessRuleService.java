package org.papyrus.domain.service;

import org.papyrus.domain.exception.BusinessRuleException;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.model.BusinessRuleType;

public interface BusinessRuleService {

	void executeCreateCondition(BusinessRuleType type, ConditionComparable conditionComparable)
			throws BusinessRuleException;

	void executeUpdateCondition(BusinessRuleType type, ConditionComparable conditionComparable)
			throws BusinessRuleException;

	void executeDeleteCondition(BusinessRuleType type, ConditionComparable conditionComparable)
			throws BusinessRuleException;

	BusinessRule deleteBusinessRule(BusinessRule businessRule);

	BusinessRule saveBusinessRule(BusinessRule businessRule);

}
