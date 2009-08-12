/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Calendar;

import org.papyrus.domain.model.BusinessRuleType;
import org.papyrus.domain.model.MailNotification;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.repository.MailNotificationRepository;
import org.papyrus.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "mailNotificationService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MailNotificationServiceImp implements MailNotificationService {
	private final BusinessRuleService businessRuleService;
	private final MailNotificationRepository repository;
	private final TaskRepository taskRepository;

	@Autowired
	public MailNotificationServiceImp(BusinessRuleService businessRuleService, MailNotificationRepository repository,
			TaskRepository taskRepository) {
		this.repository = repository;
		this.businessRuleService = businessRuleService;
		this.taskRepository = taskRepository;
	}

	public MailNotification deleteMailNotification(MailNotification mailNotification) throws Exception {
		businessRuleService.executeDeleteCondition(BusinessRuleType.MAIL_NOTIFICATION, mailNotification);
		return repository.delete(mailNotification);
	}

	public MailNotification createMailNotification(MailNotification mailNotification) throws Exception {
		businessRuleService.executeCreateCondition(BusinessRuleType.MAIL_NOTIFICATION, mailNotification);
		MailNotification mail = repository.saveOrUpdate(mailNotification);
		taskRepository.saveTask(new Task(mail, Calendar.getInstance()));
		return mail;
	}

	public MailNotification updateMailNotification(MailNotification mailNotification) throws Exception {
		businessRuleService.executeUpdateCondition(BusinessRuleType.MAIL_NOTIFICATION, mailNotification);
		return repository.saveOrUpdate(mailNotification);
	}
}
