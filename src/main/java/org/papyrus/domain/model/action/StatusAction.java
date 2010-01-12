package org.papyrus.domain.model.action;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.service.MailService;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
	public void execute(Incident incident, MailService mailService) {
		incident.setStatus(status);
	}
}
