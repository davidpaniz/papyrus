package org.papyrus.domain.model;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailNotification implements MimeMessagePreparator {
	private String subject;
	private String body;
	private String address;

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
		message.setFrom("webmaster@csonth.gov.uk"); // could be parameterized...
		message.setText(body, false);
	}

}
