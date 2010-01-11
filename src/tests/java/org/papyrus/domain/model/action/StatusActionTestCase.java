package org.papyrus.domain.model.action;

import org.junit.Assert;
import org.junit.Test;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;

public class StatusActionTestCase {
	@Test
	public void testExecutionShuoulChangeIncidentStatus() {
		Incident incident = new Incident();
		incident.setStatus(IncidentStatus.OPENED);
		StatusAction action = new StatusAction();
		action.setStatus(IncidentStatus.CLOSED);

		action.execute(incident, null);
		Assert.assertEquals(IncidentStatus.CLOSED, incident.getStatus());
	}
}
