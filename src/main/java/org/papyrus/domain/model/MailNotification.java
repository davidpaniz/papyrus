package org.papyrus.domain.model;

import javax.mail.internet.MimeMessage;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Entity
@SequenceGenerator(name = "Mail_Notification_Seq")
public class MailNotification implements MimeMessagePreparator {
	@Id
	@GeneratedValue(generator = "Mail_Notification_Seq", strategy = GenerationType.AUTO)
	private long id;

	private String subject;
	private String body;
	private String address;

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
		message.setSubject(subject);
		// FIXME "from" must be parameterized
		message.setFrom("papyrus@davidpaniz.com");
		message.setText(body, false);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailNotification other = (MailNotification) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

}
