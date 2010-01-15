package org.papyrus.domain.model.condition;

import javax.persistence.Embedded;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.User;
import org.papyrus.domain.model.businessRules.TemplateUser;

public class RequesterCondition extends Condition {
	@Embedded
	private TemplateUser templateUser;

	@Override
	public boolean test(Incident incident, User loggedUser) {
		return getComparisonOperator().compare(incident.getRequester(), templateUser.getUser(incident, loggedUser));
	}

	public void setTemplateUser(TemplateUser templateUser) {
		this.templateUser = templateUser;
	}

	public TemplateUser getTemplateUser() {
		return templateUser;
	}

}
