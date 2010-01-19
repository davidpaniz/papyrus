package org.papyrus.domain.service;

import java.util.Calendar;
import java.util.List;

import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.User;
import org.papyrus.domain.model.action.Action;
import org.papyrus.domain.model.condition.Condition;
import org.papyrus.domain.repository.ActionRepository;
import org.papyrus.domain.repository.BusinessRuleRepository;
import org.papyrus.domain.repository.ConditionRepository;
import org.papyrus.domain.repository.TaskRepository;
import org.papyrus.infra.http.SessionManager;
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
	private final SessionManager sessionManager;

	@Autowired
	public BusinessRuleServiceImp(BusinessRuleRepository repository, ConditionRepository conditionRepository,
			ActionRepository actionRepository, TaskRepository taskRepository, SessionManager sessionManager) {
		this.repository = repository;
		this.conditionRepository = conditionRepository;
		this.actionRepository = actionRepository;
		this.taskRepository = taskRepository;
		this.sessionManager = sessionManager;
	}

	public void executeCreateCondition(Incident incident) {
		List<BusinessRule> rules = repository.findCreateRules();
		execute(incident, rules);

	}

	public void executeUpdateCondition(Incident incident) {
		List<BusinessRule> rules = repository.findUpdateRules();
		execute(incident, rules);
	}

	public void executeDeleteCondition(Incident incident) {
		List<BusinessRule> rules = repository.findDeleteRules();
		execute(incident, rules);
	}

	private void execute(Incident incident, List<BusinessRule> rules) {
		User loggedUser = sessionManager.getLoggedUser();
		for (BusinessRule businessRule : rules) {
			if (businessRule.shouldExecute(incident, loggedUser)) {
				Calendar taskDate = businessRule.calculateDate();
				taskRepository.saveTask(new Task(incident, businessRule, taskDate, loggedUser));
			}
		}
	}

	public BusinessRule deleteBusinessRule(BusinessRule businessRule) {
		return repository.delete(businessRule);
	}

	public BusinessRule saveBusinessRule(BusinessRule businessRule) {
		if (businessRule.getId() != 0) {
			repository.clearConditionsAndActionsOf(businessRule);
		} else {
			businessRule = repository.saveOrUpdate(businessRule);
		}

		for (Condition condition : businessRule.getConditions()) {
			condition.setBusinessRule(businessRule);
			conditionRepository.saveOrUpdate(condition);
		}
		for (Action action : businessRule.getActions()) {
			action.setBusinessRule(businessRule);
			actionRepository.save(action);
		}

		return businessRule;
	}

	public List<BusinessRule> listBusinessRule() {
		return repository.list();
	}

	public BusinessRule loadBusinessRule(Long id) {
		BusinessRule load = repository.load(id);
		BusinessRule copy = load.copy();
		return copy;
	}
}
