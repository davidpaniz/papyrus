package org.papyrus.domain.service;

import org.papyrus.domain.model.MailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class MailServiceImp implements MailService {

	private final JavaMailSender mailSender;

	@Autowired
	public MailServiceImp(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(MailNotification notification) {
		this.mailSender.send(notification);
	}

}
