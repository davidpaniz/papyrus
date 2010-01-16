package org.papyrus.domain.model.action;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.papyrus.domain.model.MailNotification;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.User;
import org.papyrus.domain.model.businessRule.TemplateUser;
import org.papyrus.domain.service.MailService;
import org.papyrus.testutil.TestCaseUtils;

@SuppressWarnings("deprecation")
public class UserNotificationActionTestCase {
	private final Mockery mockery = TestCaseUtils.newMockery();

	private final MailService mailService = mockery.mock(MailService.class);

	@Test
	public void testExecutionShoulSendMail() {
		User user = new User();
		user.setEmail("teste@teste.com");

		NotifyUserAction action = new NotifyUserAction();
		action.setBody("Corpo");
		action.setSubject("Assunto");
		action.setTemplateUser(new TemplateUser(user));

		final MailNotification mailNotification = new MailNotification();
		mailNotification.setAddress("teste@teste.com");
		mailNotification.setBody("Corpo");
		mailNotification.setSubject("Assunto");

		mockery.checking(new Expectations() {
			{
				one(mailService).sendMail(with(any(MailNotification.class)));
			}
		});
		action.execute(null, mailService, new Task());

		mockery.assertIsSatisfied();

	}

	@Test
	public void testPrepareMail() {
		User user = new User();
		user.setEmail("teste@teste.com");

		NotifyUserAction action = new NotifyUserAction();
		action.setBody("Corpo");
		action.setSubject("Assunto");
		action.setTemplateUser(new TemplateUser(user));

		final MailNotification mailNotification = new MailNotification();
		mailNotification.setAddress("teste@teste.com");
		mailNotification.setBody("Corpo");
		mailNotification.setSubject("Assunto");

		MailNotification mail = action.prepareMail(null, new Task());
		Assert.assertEquals("Corpo", mail.getBody());
		Assert.assertEquals("Assunto", mail.getSubject());
		Assert.assertEquals("teste@teste.com", mail.getAddress());

	}
}
