package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.BusinessRuleType;
import org.papyrus.domain.model.ConditionComparable;

public interface BusinessRuleService {

	void executeCreateCondition(BusinessRuleType type, ConditionComparable conditionComparable);

	void executeUpdateCondition(BusinessRuleType type, ConditionComparable conditionComparable);

	void executeDeleteCondition(BusinessRuleType type, ConditionComparable conditionComparable);

	BusinessRule deleteBusinessRule(BusinessRule businessRule);

	BusinessRule saveBusinessRule(BusinessRule businessRule);

	List<BusinessRule> listBusinessRule();

}
