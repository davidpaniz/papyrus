package org.papyrus.domain.model.action;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.service.MailService;

@Entity
public class StatusAction extends Action {
	@Enumerated(EnumType.STRING)
	private IncidentStatus status;

	public void setStatus(IncidentStatus status) {
		this.status = status;
	}

	public IncidentStatus getStatus() {
		return status;
	}

	@Override
	public void execute(Incident incident, MailService mailService, Task task) {
		incident.setStatus(status);
	}
}
