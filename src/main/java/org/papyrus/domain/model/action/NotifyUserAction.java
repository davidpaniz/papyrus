package org.papyrus.domain.model.action;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.MailNotification;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.User;
import org.papyrus.domain.service.MailService;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class NotifyUserAction extends Action {

	@Any(metaColumn = @Column(name = "user_type"), fetch = FetchType.EAGER, optional = false)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = {
			@MetaValue(value = "USER", targetEntity = User.class),
			@MetaValue(value = "TEMPLATE", targetEntity = UserActionValues.class) })
	@JoinColumn(name = "user_id")
	private UserAction userAction;

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
		notification.setAddress(this.userAction.getUser(incident, task)
				.getEmail());
		notification.setBody(this.body);
		notification.setSubject(this.subject);
		mailService.sendMail(notification);
	}

	public void setUserAction(UserAction userAction) {
		this.userAction = userAction;
	}

	public UserAction getUserAction() {
		return userAction;
	}
}
