package org.papyrus.domain.repository;

import org.papyrus.domain.model.MailNotification;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist mailNotifications
 */
public interface MailNotificationRepository {
	/**
	 * Save an mailNotification.
	 * 
	 * @return the mailNotification persisted with the id
	 * @param the
	 *            mailNotification that will be persisted
	 */
	MailNotification saveOrUpdate(MailNotification mailNotification);

	/**
	 * @param mailNotification
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	MailNotification delete(MailNotification mailNotification);

}
