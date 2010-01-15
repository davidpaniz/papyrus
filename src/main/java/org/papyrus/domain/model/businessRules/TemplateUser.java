package org.papyrus.domain.model.businessRules;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.User;

@Embeddable
public class TemplateUser {
	@ManyToOne
	private User realUser;

	@Enumerated(EnumType.STRING)
	private UserTemplateValues template;

	public TemplateUser(User user) {
		realUser = user;
	}

	public TemplateUser(UserTemplateValues templateValues) {
		template = templateValues;
	}

	@Deprecated
	public TemplateUser() {
	}

	public User getRealUser() {
		return realUser;
	}

	public void setRealUser(User realUser) {
		this.realUser = realUser;
	}

	public UserTemplateValues getTemplate() {
		return template;
	}

	public void setTemplate(UserTemplateValues template) {
		this.template = template;
	}

	public User getUser(Incident incident, User loggedUser) {
		if (realUser != null) {
			return realUser;
		} else {
			return template.getUser(incident, loggedUser);
		}
	}

}
