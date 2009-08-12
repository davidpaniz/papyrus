/**
 * 
 */
package org.papyrus.domain.service;

import org.papyrus.domain.model.MailNotification;

/**
 * @author davidpaniz
 * 
 */
public interface MailNotificationService {
	MailNotification deleteMailNotification(MailNotification mailNotification) throws Exception;

	MailNotification createMailNotification(MailNotification mailNotification) throws Exception;

	MailNotification updateMailNotification(MailNotification mailNotification) throws Exception;
}
