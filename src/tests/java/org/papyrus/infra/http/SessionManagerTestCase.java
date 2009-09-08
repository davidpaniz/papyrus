package org.papyrus.infra.http;

import javax.servlet.http.HttpSession;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.papyrus.domain.model.User;
import org.papyrus.testutil.TestCaseUtils;

/**
 * @author davidpaniz
 * 
 */
public class SessionManagerTestCase {
	private final Mockery mockery = TestCaseUtils.newMockery();
	private final HttpSession session = mockery.mock(HttpSession.class);
	private final SessionManager sessionManager = new SessionManager();

	@Before
	public void setup() {
		sessionManager.setSession(session);
	}

	@Test
	public void whenUserLoggedIsNotLoggedShouldResturnsFalse() {
		mockery.checking(new Expectations() {
			{
				one(session).getAttribute("user");
				will(returnValue(new User()));
			}
		});

		Assert.assertFalse("Should return false when user is logged", sessionManager.isNotLogged());
		mockery.assertIsSatisfied();
	}

	@Test
	public void whenUserNOTLoggedIsNotLoggedShouldResturnsTrue() {
		mockery.checking(new Expectations() {
			{
				one(session).getAttribute("user");
				will(returnValue(null));
			}
		});

		Assert.assertTrue("Should return true when user is not logged", sessionManager.isNotLogged());
		mockery.assertIsSatisfied();
	}
}
