package org.papyrus.testutil;

import java.util.Arrays;

import org.hibernate.Session;
import org.papyrus.domain.model.Client;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.User;
import org.papyrus.domain.model.WorkOrder;

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
		User user = new Client();
		user.setEmail("a@a.com");
		user.setPassword("123456");
		user.setActive(true);

		session.save(user);
		session.flush();
		return user;
	}

	public WorkOrder createWorkOrder() {
		WorkOrder workOrder = new WorkOrder();

		session.save(workOrder);
		session.flush();
		return workOrder;
	}

	public Incident createIncident(String description, WorkOrder... workOrders) {
		Incident incident = new Incident();
		incident.setDescription(description);
		incident.setWorkOrders(Arrays.asList(workOrders));

		session.save(incident);
		session.flush();
		return incident;
	}

}
