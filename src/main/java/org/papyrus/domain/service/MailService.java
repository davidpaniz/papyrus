package org.papyrus.domain.service;

import org.papyrus.domain.model.MailNotification;

public interface MailService {
	void sendMail(MailNotification notification);

}
