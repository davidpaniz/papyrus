package org.papyrus.infra.hibernate;

import org.papyrus.domain.model.MailNotification;
import org.papyrus.domain.repository.MailNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "mailNotificationRepository")
public class MailNotificationDao implements MailNotificationRepository {

	private final HibernateTemplate template;

	@Autowired
	public MailNotificationDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public MailNotification saveOrUpdate(MailNotification mailNotification) {
		template.saveOrUpdate(mailNotification);
		return mailNotification;
	}

	public MailNotification delete(MailNotification mailNotification) {
		template.delete(mailNotification);
		return mailNotification;
	}
}
