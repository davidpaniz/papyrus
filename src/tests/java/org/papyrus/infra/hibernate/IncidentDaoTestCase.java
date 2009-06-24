package org.papyrus.infra.hibernate;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.WorkOrder;
import org.papyrus.testutil.AbstractDAOTestCase;

public class IncidentDaoTestCase extends AbstractDAOTestCase {
	private IncidentDao dao;

	@Before
	public void seTup() throws Exception {
		super.setUp();
		this.dao = new IncidentDao(super.getHibernateTemplate());
	}

	@Test
	public void whenListingIncidentsShoulReturnNoNonInitilizedBags() {
		WorkOrder workOrder1 = ObjectMother().createWorkOrder();
		WorkOrder workOrder2 = ObjectMother().createWorkOrder();

		ObjectMother().createIncident("Bla", workOrder1);
		ObjectMother().createIncident("Bla", workOrder2);

		List<Incident> list = dao.list();

		Assert.assertEquals(2, list.size());

		Assert.assertEquals(workOrder1, list.get(0)
				.getWorkOrders()
				.get(0));
	}
}
