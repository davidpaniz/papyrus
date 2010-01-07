package org.papyrus.testutil;

import org.hibernate.Session;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.User;

/**
 * @author davidpaniz
 * 
 *         Class that helps create objects in tests
 */
public class ObjectMother {
	private final Session session;

	public ObjectMother(Session session) {
		this.session = session;
	}

	public User createUser() {
		User user = new User();
		user.setEmail("a@a.com");
		user.setPassword("123456");
		user.setActive(true);

		session.save(user);
		session.flush();
		return user;
	}

	public Incident createIncident(String description) {
		Incident incident = new Incident();
		incident.setDescription(description);
		// incident.setWorkOrders(Arrays.asList(workOrders));

		session.save(incident);
		session.flush();
		return incident;
	}

}
