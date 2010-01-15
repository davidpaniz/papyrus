package org.papyrus.domain.model.action;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.MailNotification;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.User;
import org.papyrus.domain.model.businessRules.TemplateUser;
import org.papyrus.domain.service.MailService;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class NotifyUserAction extends Action {
	@Embedded
	private TemplateUser templateUser;
	private String subject;
	private String body;

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
	public void execute(Incident incident, MailService mailService, Task task) {
		MailNotification notification = new MailNotification();
		notification.setAddress(this.getUser(incident, task)
				.getEmail());
		notification.setBody(this.body);
		notification.setSubject(this.subject);
		mailService.sendMail(notification);
	}

	public User getUser(Incident incident, Task task) {
		return templateUser.getUser(incident, task.getLoggedUserWhenCreated());
	}

	@Override
	public Action specificCopy() {
		NotifyUserAction copy = new NotifyUserAction();
		copy.body = this.body;
		copy.subject = this.subject;
		copy.templateUser = this.templateUser;
		return copy;
	}

	public void setTemplateUser(TemplateUser templateUser) {
		this.templateUser = templateUser;
	}

	public TemplateUser getTemplateUser() {
		return templateUser;
	}
}
