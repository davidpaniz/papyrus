package org.papyrus.domain.model.action;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.User;
import org.papyrus.domain.service.MailService;

public class UserNotificationAction extends Action {
	private User user;
	private String subject;
	private String body;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	@Override
	public void execute(Incident incident, MailService mailService) {

	}
}
