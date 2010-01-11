package org.papyrus.domain.model.action;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.service.MailService;

public class StatusAction extends Action {
	private IncidentStatus status;

	public void setStatus(IncidentStatus status) {
		this.status = status;
	}

	public IncidentStatus getStatus() {
		return status;
	}

	@Override
	public void execute(Incident incident, MailService mailService) {
		incident.setStatus(status);
	}
}
