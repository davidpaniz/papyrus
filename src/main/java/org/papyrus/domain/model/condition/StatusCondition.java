package org.papyrus.domain.model.condition;

import javax.persistence.Entity;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.User;

@Entity
public class StatusCondition extends Condition {
	IncidentStatus status;

	public IncidentStatus getStatus() {
		return status;
	}

	public void setStatus(IncidentStatus status) {
		this.status = status;
	}

	@Override
	public boolean test(Incident incident, User loggedUser) {
		return getComparisonOperator().compare(incident.getStatus(), status);
	}
}
