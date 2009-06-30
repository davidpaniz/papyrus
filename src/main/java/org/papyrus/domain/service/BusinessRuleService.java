package org.papyrus.domain.service;

import org.papyrus.domain.model.ConditionType;

public interface BusinessRuleService {

	void executeCreateCondition(ConditionType incident);
}
