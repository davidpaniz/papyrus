package org.papyrus.domain.service;

import org.papyrus.domain.model.MailNotification;
import org.papyrus.infra.http.DoesntRequiresLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "mailService")
public class MailServiceImp implements MailService {

	private final JavaMailSender mailSender;

	@Autowired
	public MailServiceImp(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@DoesntRequiresLogin
	public void sendMail(MailNotification notification) {
		this.mailSender.send(notification);
	}

}
