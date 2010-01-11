package org.papyrus.domain.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.Condition;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.action.Action;
import org.papyrus.domain.repository.ActionRepository;
import org.papyrus.domain.repository.BusinessRuleRepository;
import org.papyrus.domain.repository.ConditionRepository;
import org.papyrus.domain.repository.TaskRepository;
import org.papyrus.testutil.TestCaseUtils;

public class BusinessRuleServiceTestCase {
	private final Mockery mockery = TestCaseUtils.newMockery();

	private final BusinessRuleRepository repository = mockery.mock(BusinessRuleRepository.class);
	private final ConditionRepository conditionRepository = mockery.mock(ConditionRepository.class);
	private final ActionRepository actionRepository = mockery.mock(ActionRepository.class);
	private final TaskRepository taskRepository = mockery.mock(TaskRepository.class);

	private final BusinessRuleServiceImp serviceImp = new BusinessRuleServiceImp(repository, conditionRepository,
			actionRepository, taskRepository);

	@Test
	public void whenSaveBusinessRuleSaveItsActionsAndConditions() {
		final BusinessRule br = new BusinessRule();

		List<Condition> conditions = new ArrayList<Condition>();
		final Condition cond1 = new Condition();
		conditions.add(cond1);
		final Condition cond2 = new Condition();
		conditions.add(cond2);

		List<Action> actions = new ArrayList<Action>();
		final Action ac1 = new Action();
		actions.add(ac1);
		final Action ac2 = new Action();
		actions.add(ac2);

		br.setConditions(conditions);
		br.setActions(actions);
		mockery.checking(new Expectations() {
			{
				one(repository).saveOrUpdate(br);
				will(returnValue(br));
				one(conditionRepository).saveOrUpdate(cond1);
				will(returnValue(cond1));
				one(conditionRepository).saveOrUpdate(cond2);
				will(returnValue(cond2));
				one(actionRepository).save(ac1);
				will(returnValue(ac1));
				one(actionRepository).save(ac2);
				will(returnValue(ac2));

			}
		});

		serviceImp.saveBusinessRule(br);
		mockery.assertIsSatisfied();
		Assert.assertEquals(br, ac1.getBusinessRule());
		Assert.assertEquals(br, cond1.getBusinessRule());
	}

	@Test
	public void whenExecuteABusinessRulesAllActionsShouldBeExecutedIfConditionsSatisfact() {
		final BusinessRule br1 = mockery.mock(BusinessRule.class, "BusinessRule1");
		final BusinessRule br2 = mockery.mock(BusinessRule.class, "BusinessRule2");
		final List<BusinessRule> businessRules = new ArrayList<BusinessRule>();
		businessRules.add(br1);
		businessRules.add(br2);

		final Incident conditionComparable = new Incident();
		conditionComparable.setId(1);

		final Incident oldValue = null;

		final List<Action> actions = new ArrayList<Action>();
		final Action action = mockery.mock(Action.class);
		actions.add(action);

		mockery.checking(new Expectations() {
			{
				Calendar taskDate = Calendar.getInstance();
				one(repository).findCreateRules();
				will(returnValue(businessRules));

				one(repository).load(Incident.class, 1L);
				will(returnValue(oldValue));
				one(repository).unlock(oldValue);
				one(br1).shouldExecute(oldValue, conditionComparable);
				will(returnValue(false));

				one(br2).shouldExecute(oldValue, conditionComparable);
				will(returnValue(true));
				one(br2).calculateDate();
				will(returnValue(taskDate));
				one(br2).getActions();
				will(returnValue(actions));
				Incident detail = new Incident();
				// one(action).detail(oldValue, conditionComparable);
				will(returnValue(detail));
				// one(conditionComparableRepository).saveTemplate(detail);
				// one(taskRepository).saveTask(with(any(Task.class)));

			}
		});

		serviceImp.executeCreateCondition(conditionComparable);
		mockery.assertIsSatisfied();
	}
}
