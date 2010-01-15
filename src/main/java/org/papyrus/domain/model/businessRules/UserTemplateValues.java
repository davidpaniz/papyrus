package org.papyrus.domain.model.businessRules;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.User;

public enum UserTemplateValues {
	CURRENT_USER {
		@Override
		public User getUser(Incident incident, User loggedUser) {
			return loggedUser;
		}
	},
	REQUESTER {
		@Override
		public User getUser(Incident incident, User loggedUser) {
			return incident.getRequester();
		}
	},
	RESPONSABLE {
		@Override
		public User getUser(Incident incident, User loggedUser) {
			return incident.getResponsable();
		}
	};

	public abstract User getUser(Incident incident, User loggedUser);
}
