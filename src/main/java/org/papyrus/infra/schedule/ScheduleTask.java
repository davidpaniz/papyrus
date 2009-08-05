package org.papyrus.infra.schedule;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.repository.ConditionComparableRepository;
import org.papyrus.domain.repository.TaskRepository;
import org.papyrus.domain.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleTask extends TimerTask {
	private static final Logger logger = Logger.getLogger(ScheduleTask.class);
	private final ConditionComparableRepository conditionComparableRepository;
	private final MailService mailService;
	private final TaskRepository taskRepository;

	@Autowired
	public ScheduleTask(TaskRepository taskRepository, ConditionComparableRepository conditionComparableRepository,
			MailService mailService) {
		this.taskRepository = taskRepository;
		this.conditionComparableRepository = conditionComparableRepository;
		this.mailService = mailService;
	}

	@Override
	public void run() {
		logger.trace("Running Scheduler");
		for (Task task : taskRepository.taskToExecute()) {
			ConditionComparable detail = task.getDetail();
			detail.execute(mailService, conditionComparableRepository);
		}
	}
}
