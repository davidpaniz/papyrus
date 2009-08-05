package org.papyrus.domain.service;

import org.papyrus.domain.model.MailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "mailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
