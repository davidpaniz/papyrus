package org.papyrus.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class ConditionTestCase {
	@Test
	public void testShouldReturnTrueWhenExpressionsAreEqualsAndOperatiorIsEQ() {
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
	public void testShouldReturnFalseWhenExpressionsAreEqualsAndOperatiorIsNEQ() {
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
	public void testShouldReturnFalseWhenExpressionsAreDifferentAndOperatiorIsEQ() {
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
	public void testShouldReturnTrueWhenExpressionsAreDifferentAndOperatiorIsNEQ() {
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
	public void testShouldNotThrowsExceptionWhenNullObjectSentAndExpressionDoesNotUseThisReference() {
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
	public void testShouldNotThrowsExceptionWhenNullObjectSentAndExpressionDoesNotUseThisReferenceReturnigFalse() {
		Incident newValue = new Incident();
		newValue.setDescription("a");

		Condition c = new Condition();
		c.setExpression1("${description}");
		c.setExpression2("b");
		c.setComparisonOperator(ConditionComparisonOperator.EQ);

		boolean test = c.test(null, newValue);
		Assert.assertFalse(test);
	}

}
