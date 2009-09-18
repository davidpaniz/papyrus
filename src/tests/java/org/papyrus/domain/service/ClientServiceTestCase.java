package org.papyrus.domain.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.papyrus.domain.repository.ClientRepository;
import org.papyrus.testutil.TestCaseUtils;

public class ClientServiceTestCase {
	private final Mockery mockery = TestCaseUtils.newMockery();
	private final ClientRepository repository = mockery.mock(ClientRepository.class);
	private final ClientServiceImp serviceImp = new ClientServiceImp(repository);

	@Test
	public void whenInvokeListMyIncidentsWithAllArgsNullShouldInvokeRepositoryWithArgsNull() throws Exception {

		mockery.checking(new Expectations() {
			{
				one(repository).listMyInicidents(null, null, null);
			}
		});

		serviceImp.listMyIncidents(null, null, null);
		mockery.assertIsSatisfied();
	}
}
