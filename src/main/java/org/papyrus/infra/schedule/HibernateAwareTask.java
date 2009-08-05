package org.papyrus.infra.schedule;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class HibernateAwareTask {

	private final SessionFactory sessionFactory;

	public HibernateAwareTask(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void execute(HibernateRunnable runnable) {
		TransactionSynchronizationManager.bindResource(this.sessionFactory, new SessionHolder(
				this.sessionFactory.openSession()));
		this.sessionFactory.getCurrentSession()
				.beginTransaction();

		runnable.run();

		this.sessionFactory.getCurrentSession()
				.getTransaction()
				.commit();
	}
}
