package org.papyrus.util;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.papyrus.domain.model.Category;
import org.papyrus.domain.model.Company;
import org.papyrus.domain.model.Impact;
import org.papyrus.domain.model.Priority;
import org.papyrus.domain.model.Role;
import org.papyrus.domain.model.Urgency;
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
		try {
			Urgency lowUrgency = new Urgency();
			lowUrgency.setDescription("Low");
			session.save(lowUrgency);

			Urgency mediumUrgency = new Urgency();
			mediumUrgency.setDescription("Medium");
			session.save(mediumUrgency);

			Urgency highUrgency = new Urgency();
			highUrgency.setDescription("High");
			session.save(highUrgency);

			Impact lowImpact = new Impact();
			lowImpact.setDescription("Low");
			session.save(lowImpact);

			Impact mediumImpact = new Impact();
			mediumImpact.setDescription("Medium");
			session.save(mediumImpact);

			Impact highImpact = new Impact();
			highImpact.setDescription("High");
			session.save(highImpact);

			// Priority 5
			Priority priority1_5 = new Priority();
			setPriorityValue(priority1_5, "Low x Low", 172800, 86400, lowUrgency, lowImpact);
			session.save(priority1_5);
			// Priority 4
			Priority priority2_4 = new Priority();
			setPriorityValue(priority2_4, "Low x Medium", 129600, 10800, lowUrgency, mediumImpact);
			session.save(priority2_4);

			Priority priority3_4 = new Priority();
			setPriorityValue(priority3_4, "Medium x Low", 129600, 10800, mediumUrgency, lowImpact);
			session.save(priority3_4);

			// Priority 3
			Priority priority4_3 = new Priority();
			setPriorityValue(priority4_3, "Low x High", 86400, 7200, lowUrgency, highImpact);
			session.save(priority4_3);

			Priority priority5_3 = new Priority();
			setPriorityValue(priority5_3, "High x Low", 86400, 7200, highUrgency, lowImpact);
			session.save(priority5_3);

			Priority priority6_3 = new Priority();
			setPriorityValue(priority6_3, "Medium x Medium", 86400, 7200, mediumUrgency, mediumImpact);
			session.save(priority6_3);

			// Priority 2
			Priority priority7_2 = new Priority();
			setPriorityValue(priority7_2, "Medium x High", 7200, 1200, mediumUrgency, highImpact);
			session.save(priority7_2);

			Priority priority8_2 = new Priority();
			setPriorityValue(priority8_2, "High x Medium", 7200, 1200, highUrgency, mediumImpact);
			session.save(priority8_2);

			// Priority 1
			Priority priority9_1 = new Priority();
			setPriorityValue(priority9_1, "High x High", 3600, 600, highUrgency, highImpact);
			session.save(priority9_1);

			Category hardware = new Category();
			hardware.setName("Hardware");
			session.save(hardware);

			Category hardware1 = new Category();
			hardware1.setName("Sub1");
			hardware1.setParent(hardware);
			session.save(hardware1);

			Category hardware2 = new Category();
			hardware2.setName("Sub2");
			hardware2.setParent(hardware);
			session.save(hardware2);

			Category software = new Category();
			software.setName("Software");
			session.save(software);

			Category software1 = new Category();
			software1.setName("Sub1");
			software1.setParent(software);
			session.save(software1);

			Category software2 = new Category();
			software2.setName("Sub2");
			software2.setParent(software);
			session.save(software2);

			Company company = new Company();
			company.setName("Example Company");
			session.save(company);

			User admin = new User();
			setValuesToUser(admin, "Admin", true, "a@a.com", "123", company, priority1_5, Role.ADMIN);
			session.save(admin);

			User staff = new User();
			setValuesToUser(staff, "Staff", true, "s@a.com", "123", company, priority1_5, Role.STAFF);
			session.save(staff);

			User client = new User();
			setValuesToUser(client, "Cliente", true, "c@a.com", "123", company, priority1_5, Role.CLIENT);
			session.save(client);

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	private void setPriorityValue(Priority priority, String description, int duration, int response, Urgency urgency,
			Impact impact) {
		priority.setDescription(description);
		priority.setDuration(duration);
		priority.setResponse(response);
		priority.setUrgency(urgency);
		priority.setImpact(impact);
	}

	private void setValuesToUser(User user, String name, boolean active, String email, String password,
			Company company, Priority priority, Role role) {
		user.setName(name);
		user.setActive(active);
		user.setEmail(email);
		user.setPassword(password);
		user.setCompany(company);
		user.setPriority(priority);
		user.setRole(role);
	}

	public static void main(String... args) throws IOException {
		Bootstrap bootstrap = new Bootstrap(new HibernateUtils(new PropertiesLoader()));
		bootstrap.loadBootstrap();
	}
}
