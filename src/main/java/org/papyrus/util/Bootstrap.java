package org.papyrus.util;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.papyrus.domain.model.Client;
import org.papyrus.domain.model.Company;
import org.papyrus.domain.model.Role;
import org.papyrus.domain.model.Staff;
import org.papyrus.domain.model.User;

public class Bootstrap {

	private final HibernateUtils hibernateUtils;

	public Bootstrap(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	public void loadBootstrap() throws HibernateException, IOException {
		SessionFactory sessionFactory = hibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Company company = new Company();
		company.setName("Example Company");
		session.save(company);

		User admin = new Staff();
		setValuesToUser(admin, "Admin", true, "a@a.com", "123", Role.ADMIN);

		User staff = new Staff();
		setValuesToUser(staff, "Staff", true, "s@a.com", "123", Role.STAFF);

		User client = new Client();
		setValuesToUser(client, "Cliente", true, "s@a.com", "123", Role.CLIENT);

		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	private void setValuesToUser(User user, String name, boolean active, String email, String password, Role role) {
		user.setName(name);
		user.setActive(active);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
	}
}
