package org.papyrus.infra.hibernate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.papyrus.domain.model.User;
import org.papyrus.testutil.AbstractDAOTestCase;

public class UserDaoTestCase extends AbstractDAOTestCase {
	private UserDao dao;

	@Before
	public void seTup() throws Exception {
		super.setUp();
		this.dao = new UserDao(super.getHibernateTemplate());
	}

	@Test
	public void whenTryLoginWithAValidUserShoulReturnTheUser() {
		User user = ObjectMother().createUser();
		Assert.assertNotNull(dao.login(user));
	}

	@Test
	public void whenTryLoginWithWrongEmailShoulReturnNull() {
		ObjectMother().createUser();
		User user = new User();
		user.setEmail("dsadsa@dsadas.com");
		user.setPassword("123456");

		Assert.assertNull(dao.login(user));
	}

	@Test
	public void whenTryLoginWithWrongPasswordShoulReturnNull() {
		ObjectMother().createUser();
		User user = new User();
		user.setEmail("a@a.com");
		user.setPassword("098876");

		Assert.assertNull(dao.login(user));
	}
}
