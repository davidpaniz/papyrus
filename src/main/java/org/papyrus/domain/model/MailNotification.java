package org.papyrus.domain.model;

import javax.mail.internet.MimeMessage;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.papyrus.domain.repository.ConditionComparableRepository;
import org.papyrus.domain.service.MailService;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Entity
@SequenceGenerator(name = "Mail_Notification_Seq")
public class MailNotification implements MimeMessagePreparator, ConditionComparable {
	@Id
	@GeneratedValue(generator = "Mail_Notification_Seq", strategy = GenerationType.AUTO)
	private long id;

	private String subject;
	private String body;
	private String address;

	private boolean template;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void prepare(MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setTo(this.address);
		// FIXME "from" must be parameterized
		message.setFrom("papyrus@davidpaniz.com");
		message.setText(body, false);
	}

	public void execute(MailService mailService, ConditionComparableRepository conditionComparableRepository) {
		mailService.sendMail(this);
	}

	public void activeTemplate() {
		this.template = false;
	}

	public void asTemplate() {
		this.template = true;
	}

	public boolean isTemplate() {
		return template;
	}

	public void setTemplate(boolean template) {
		this.template = template;
	}
}
