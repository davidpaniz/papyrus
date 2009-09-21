package org.papyrus.domain.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.papyrus.domain.model.Client;
import org.papyrus.domain.model.User;
import org.papyrus.domain.repository.ClientRepository;
import org.papyrus.infra.http.SessionManager;
import org.papyrus.testutil.TestCaseUtils;

public class ClientServiceTestCase {
	private final Mockery mockery = TestCaseUtils.newMockery();
	private final ClientRepository repository = mockery.mock(ClientRepository.class);
	private final SessionManager sessionManager = mockery.mock(SessionManager.class);
	private final ClientServiceImp serviceImp = new ClientServiceImp(repository, sessionManager);

	@Test
	public void whenInvokeListMyIncidentsWithAllArgsNullShouldInvokeRepositoryWithArgsNull() throws Exception {
		final User user = new Client();
		mockery.checking(new Expectations() {
			{
				one(sessionManager).getLoggedUser();
				will(returnValue(user));
				one(repository).listUserInicidents(user, null, null, null);
			}
		});

		serviceImp.listMyIncidents(null, null, null);
		mockery.assertIsSatisfied();
	}
}
