package org.papyrus.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.papyrus.domain.exception.BusinessRuleException;

public class ConditionTestCase {
	@Test
	public void testShouldReturnTrueWhenExpressionsAreEqualsAndOperatiorIsEQ() throws BusinessRuleException {
		Incident oldValue = new Incident();
		oldValue.setDescription("a");
		Incident newValue = new Incident();
		newValue.setDescription("a");

		Condition c = new Condition();
		c.setExpression1("#{description}");
		c.setExpression2("${description}");
		c.setComparisonOperator(ConditionComparisonOperator.EQ);

		boolean test = c.test(oldValue, newValue);
		Assert.assertTrue(test);
	}

	@Test
	public void testShouldReturnFalseWhenExpressionsAreEqualsAndOperatiorIsNEQ() throws BusinessRuleException {
		Incident oldValue = new Incident();
		oldValue.setDescription("a");
		Incident newValue = new Incident();
		newValue.setDescription("a");

		Condition c = new Condition();
		c.setExpression1("#{description}");
		c.setExpression2("${description}");
		c.setComparisonOperator(ConditionComparisonOperator.NEQ);

		boolean test = c.test(oldValue, newValue);
		Assert.assertFalse(test);
	}

	@Test
	public void testShouldReturnFalseWhenExpressionsAreDifferentAndOperatiorIsEQ() throws BusinessRuleException {
		Incident oldValue = new Incident();
		oldValue.setDescription("a");
		Incident newValue = new Incident();
		newValue.setDescription("b");

		Condition c = new Condition();
		c.setExpression1("#{description}");
		c.setExpression2("${description}");
		c.setComparisonOperator(ConditionComparisonOperator.EQ);

		boolean test = c.test(oldValue, newValue);
		Assert.assertFalse(test);
	}

	@Test
	public void testShouldReturnTrueWhenExpressionsAreDifferentAndOperatiorIsNEQ() throws BusinessRuleException {
		Incident oldValue = new Incident();
		oldValue.setDescription("a");
		Incident newValue = new Incident();
		newValue.setDescription("b");

		Condition c = new Condition();
		c.setExpression1("#{description}");
		c.setExpression2("${description}");
		c.setComparisonOperator(ConditionComparisonOperator.NEQ);

		boolean test = c.test(oldValue, newValue);
		Assert.assertTrue(test);
	}

	@Test
	public void testShouldNotThrowsExceptionWhenNullObjectSentAndExpressionDoesNotUseThisReference()
			throws BusinessRuleException {
		Incident newValue = new Incident();
		newValue.setDescription("a");

		Condition c = new Condition();
		c.setExpression1("${description}");
		c.setExpression2("a");
		c.setComparisonOperator(ConditionComparisonOperator.EQ);

		boolean test = c.test(null, newValue);
		Assert.assertTrue(test);
	}

	@Test
	public void testShouldNotThrowsExceptionWhenNullObjectSentAndExpressionDoesNotUseThisReferenceReturnigFalse()
			throws BusinessRuleException {
		Incident newValue = new Incident();
		newValue.setDescription("a");

		Condition c = new Condition();
		c.setExpression1("${description}");
		c.setExpression2("b");
		c.setComparisonOperator(ConditionComparisonOperator.EQ);

		boolean test = c.test(null, newValue);
		Assert.assertFalse(test);
	}

	@Test(expected = BusinessRuleException.class)
	public void testShouldThrowsExceptionWhenExpressionHaveUndefinedField() throws BusinessRuleException {
		Incident newValue = new Incident();
		newValue.setDescription("a");

		Condition c = new Condition();
		c.setExpression1("${testing}");
		c.setExpression2("b");
		c.setComparisonOperator(ConditionComparisonOperator.GT);

		c.test(null, newValue);
	}
}
