/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.exception.BusinessRuleException;
import org.papyrus.domain.model.Action;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.model.ConditionType;
import org.papyrus.domain.repository.BusinessRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "businessRuleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BusinessRuleServiceImp implements BusinessRuleService {
	private final BusinessRuleRepository repository;

	@Autowired
	public BusinessRuleServiceImp(BusinessRuleRepository repository) {
		this.repository = repository;
	}

	public void executeCreateCondition(ConditionType type, ConditionComparable conditionComparable)
			throws BusinessRuleException {
		List<BusinessRule> rules = repository.findCreateRules(type);
		for (BusinessRule businessRule : rules) {
			ConditionComparable oldValue = repository.load(type.getType(), conditionComparable.getId());
			if (businessRule.shouldExecute(oldValue, conditionComparable)) {
				List<Action> actions = businessRule.getActions();
				for (Action action : actions) {
					action.doAction();
				}
			}
		}

	}

	public BusinessRule deleteBusinessRule(BusinessRule businessRule) {
		return repository.delete(businessRule);
	}

	public BusinessRule saveBusinessRule(BusinessRule businessRule) {
		return repository.saveOrUpdate(businessRule);
	}
}
