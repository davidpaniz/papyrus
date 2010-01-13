package org.papyrus.domain.model.action;

import org.junit.Assert;
import org.junit.Test;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.User;

public class UserNotificationActionTestCase {
	@Test
	public void testExecutionShuoulChangeIncidentStatus() {
		Incident incident = new Incident();
		incident.setStatus(IncidentStatus.OPENED);

		User user = new User();
		user.setEmail("teste@teste.com");

		NotifyUserAction action = new NotifyUserAction();
		action.setBody("Corpo");
		action.setSubject("Assunto");
		action.setUserAction(user);

		action.execute(incident, null, null);
		Assert.assertEquals(IncidentStatus.CLOSED, incident.getStatus());

	}
}
