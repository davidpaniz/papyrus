package org.papyrus.domain.model.condition;

import org.junit.Assert;
import org.junit.Test;
import org.papyrus.domain.model.ConditionComparisonOperator;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;

public class StatusConditionTestCase {
	@Test
	public void testShouldReturnTrueWhenStatusIsEqualAndComparisonIsEqual() {
		Incident incident = new Incident();
		incident.setStatus(IncidentStatus.CLOSED);

		StatusCondition c = new StatusCondition();
		c.setStatus(IncidentStatus.CLOSED);
		c.setComparisonOperator(ConditionComparisonOperator.EQ);

		boolean test = c.test(incident);
		Assert.assertTrue(test);
	}

	@Test
	public void testShouldReturnFalseWhenStatusIsNotEqualAndComparisonIsEqual() {
		Incident incident = new Incident();
		incident.setStatus(IncidentStatus.OPENED);

		StatusCondition c = new StatusCondition();
		c.setStatus(IncidentStatus.CLOSED);
		c.setComparisonOperator(ConditionComparisonOperator.EQ);

		boolean test = c.test(incident);
		Assert.assertFalse(test);
	}

	@Test
	public void testShouldReturnTrueWhenStatusIsNotEqualAndComparisonIsNotEqual() {
		Incident incident = new Incident();
		incident.setStatus(IncidentStatus.OPENED);

		StatusCondition c = new StatusCondition();
		c.setStatus(IncidentStatus.CLOSED);
		c.setComparisonOperator(ConditionComparisonOperator.NEQ);

		boolean test = c.test(incident);
		Assert.assertTrue(test);
	}
}
