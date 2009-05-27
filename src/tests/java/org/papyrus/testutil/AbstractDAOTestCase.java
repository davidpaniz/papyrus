package org.papyrus.testutil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class AbstractDAOTestCase {
	private SessionFactory sessionFactory;
	private ObjectMother objectMother;
	private HibernateTemplate hibernateTemplate;

	protected Session session() {
		return this.sessionFactory.getCurrentSession();
	}

	protected HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	protected ObjectMother ObjectMother() {
		return this.objectMother;
	}

	/**
	 * First commits the transaction, and then clears the session
	 */
	protected void commit() {
		this.sessionFactory.getCurrentSession()
				.getTransaction()
				.commit();
	}

	@Before
	public void setUp() throws Exception {
		Configuration config = new AnnotationConfiguration();
		config.configure("/hibernate-tests.cfg.xml");
		this.sessionFactory = config.buildSessionFactory();
		session().beginTransaction();
		this.objectMother = new ObjectMother(session());
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@After
	public void tearDown() throws Exception {
		if (this.sessionFactory != null) {
			this.sessionFactory.close();
		}
	}
}
