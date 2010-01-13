package org.papyrus.domain.model.action;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.User;

public enum UserActionValuesTemplate {
	CURRENT_USER {
		@Override
		public User getUser(Incident incident, Task task) {
			return task.getLoggedUserWhenCreated();
		}
	},
	REQUESTER {
		@Override
		public User getUser(Incident incident, Task task) {
			return incident.getRequester();
		}
	},
	RESPONSABLE {
		@Override
		public User getUser(Incident incident, Task task) {
			return incident.getResponsable();
		}
	};

	public abstract User getUser(Incident incident, Task task);
}
