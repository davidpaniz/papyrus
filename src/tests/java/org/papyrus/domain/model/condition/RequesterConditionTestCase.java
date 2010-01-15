package org.papyrus.domain.model.condition;

import org.junit.Assert;
import org.junit.Test;
import org.papyrus.domain.model.ConditionComparisonOperator;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.User;
import org.papyrus.domain.model.businessRules.TemplateUser;

public class RequesterConditionTestCase {
	@Test
	public void testShouldReturnTrueWhenStatusIsEqualAndComparisonIsEqual() {
		User user = new User();

		Incident incident = new Incident();
		incident.setRequester(user);

		RequesterCondition c = new RequesterCondition();
		c.setTemplateUser(new TemplateUser(user));
		c.setComparisonOperator(ConditionComparisonOperator.EQ);

		boolean test = c.test(incident, null);
		Assert.assertTrue(test);
	}
}
