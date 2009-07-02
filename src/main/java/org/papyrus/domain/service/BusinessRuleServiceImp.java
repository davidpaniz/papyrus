/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Action;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionType;
import org.papyrus.domain.repository.BusinessRuleRepository;
import org.papyrus.domain.repository.ConditionRepository;
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
	private final ConditionRepository conditionRepository;

	@Autowired
	public BusinessRuleServiceImp(BusinessRuleRepository repository, ConditionRepository conditionRepository) {
		this.repository = repository;
		this.conditionRepository = conditionRepository;
	}

	public void executeCreateCondition(ConditionType incident) {
		List<BusinessRule> rules = conditionRepository.findCreateRules(incident);
		for (BusinessRule businessRule : rules) {
			List<Action> actions = businessRule.getActions();
			for (Action action : actions) {
				action.doAction();
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
