/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Calendar;
import java.util.List;

import org.papyrus.domain.model.Action;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.BusinessRuleType;
import org.papyrus.domain.model.Condition;
import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.repository.ActionRepository;
import org.papyrus.domain.repository.BusinessRuleRepository;
import org.papyrus.domain.repository.ConditionComparableRepository;
import org.papyrus.domain.repository.ConditionRepository;
import org.papyrus.domain.repository.TaskRepository;
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
	private final ActionRepository actionRepository;
	private final TaskRepository taskRepository;
	private final ConditionComparableRepository conditionComparableRepository;

	@Autowired
	public BusinessRuleServiceImp(BusinessRuleRepository repository, ConditionRepository conditionRepository,
			ActionRepository actionRepository, ConditionComparableRepository conditionComparableRepository,
			TaskRepository taskRepository) {
		this.repository = repository;
		this.conditionRepository = conditionRepository;
		this.actionRepository = actionRepository;
		this.conditionComparableRepository = conditionComparableRepository;
		this.taskRepository = taskRepository;
	}

	public void executeCreateCondition(BusinessRuleType type, ConditionComparable conditionComparable) {
		List<BusinessRule> rules = repository.findCreateRules(type);
		execute(type, conditionComparable, rules);

	}

	public void executeUpdateCondition(BusinessRuleType type, ConditionComparable conditionComparable) {
		List<BusinessRule> rules = repository.findUpdateRules(type);
		execute(type, conditionComparable, rules);
	}

	public void executeDeleteCondition(BusinessRuleType type, ConditionComparable conditionComparable) {
		List<BusinessRule> rules = repository.findDeleteRules(type);
		execute(type, conditionComparable, rules);
	}

	private void execute(BusinessRuleType type, ConditionComparable conditionComparable, List<BusinessRule> rules) {
		ConditionComparable oldValue = repository.load(type.getType(), conditionComparable.getId());
		for (BusinessRule businessRule : rules) {
			if (businessRule.shouldExecute(oldValue, conditionComparable)) {
				Calendar taskDate = businessRule.calculateDate();
				List<Action> actions = businessRule.getActions();
				for (Action action : actions) {
					ConditionComparable detail = conditionComparableRepository.saveTemplate(action.detail(oldValue,
							conditionComparable));
					taskRepository.saveTask(new Task(detail, taskDate));
				}
			}
		}

		repository.unlock(oldValue);
	}

	public BusinessRule deleteBusinessRule(BusinessRule businessRule) {
		return repository.delete(businessRule);
	}

	public BusinessRule saveBusinessRule(BusinessRule businessRule) {
		BusinessRule persistedBusinessRule = repository.saveOrUpdate(businessRule);
		for (Condition condition : businessRule.getConditions()) {
			condition.setBusinessRule(persistedBusinessRule);
			conditionRepository.saveOrUpdate(condition);
		}
		for (Action action : businessRule.getActions()) {
			action.setBusinessRule(persistedBusinessRule);
			actionRepository.save(action);
		}

		return persistedBusinessRule;
	}

	public List<BusinessRule> listBusinessRule() {
		return repository.list();
	}

	public BusinessRule loadBusinessRule(Long id) {
		BusinessRule load = repository.load(id);
		return load.copy();
	}
}
