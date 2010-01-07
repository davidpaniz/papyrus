package org.papyrus.domain.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.papyrus.domain.model.User;
import org.papyrus.domain.repository.IncidentRepository;
import org.papyrus.domain.repository.UserRepository;
import org.papyrus.infra.http.SessionManager;
import org.papyrus.testutil.TestCaseUtils;

public class UserServiceTestCase {
	private final Mockery mockery = TestCaseUtils.newMockery();
	private final UserRepository repository = mockery.mock(UserRepository.class);
	private final IncidentRepository incidentRepository = mockery.mock(IncidentRepository.class);
	private final SessionManager sessionManager = mockery.mock(SessionManager.class);
	private final UserServiceImp serviceImp = new UserServiceImp(repository, sessionManager, incidentRepository);

	@Test
	public void whenInvokeListMyIncidentsWithAllArgsNullShouldInvokeRepositoryWithArgsNull() throws Exception {
		final User user = new User();
		mockery.checking(new Expectations() {
			{
				one(sessionManager).getLoggedUser();
				will(returnValue(user));
				one(incidentRepository).listUserInicidents(user, null, null, null);
			}
		});

		serviceImp.listMyIncidents(null, null, null);
		mockery.assertIsSatisfied();
	}
}
