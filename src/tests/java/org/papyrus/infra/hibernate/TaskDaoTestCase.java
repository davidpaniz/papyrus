package org.papyrus.infra.hibernate;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.Task;
import org.papyrus.testutil.AbstractDAOTestCase;

public class TaskDaoTestCase extends AbstractDAOTestCase {
	private TaskDao dao;

	@Before
	public void seTup() throws Exception {
		super.setUp();
		this.dao = new TaskDao(super.getHibernateTemplate());
	}

	@Test
	public void whenTryLoginWithWrongPasswordShoulReturnNull() {
		Incident incident1 = ObjectMother().createIncident("teste1");
		Incident incident2 = ObjectMother().createIncident("teste2");

		Task task1 = new Task(incident1, null, null);
		dao.saveTask(task1);

		Task task2 = new Task(incident1, null, null);
		dao.saveTask(task2);

		Task task3 = new Task(incident2, null, null);
		dao.saveTask(task3);

		List<Task> tasksOfIncident1 = dao.tasksOf(incident1);
		List<Task> tasksOfIncident2 = dao.tasksOf(incident2);
		Assert.assertEquals(2, tasksOfIncident1.size());
		Assert.assertEquals(1, tasksOfIncident2.size());
		Assert.assertEquals(task3.getId(), tasksOfIncident2.get(0)
				.getId());
	}
}
