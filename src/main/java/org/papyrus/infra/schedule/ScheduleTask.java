package org.papyrus.infra.schedule;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.repository.ConditionComparableRepository;
import org.papyrus.domain.repository.TaskRepository;
import org.papyrus.domain.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTask extends TimerTask {
	private static final Logger logger = Logger.getLogger(ScheduleTask.class);
	private final ConditionComparableRepository conditionComparableRepository;
	private final MailService mailService;
	private final TaskRepository taskRepository;
	private final HibernateAwareTask hibernateAwareTask;

	@Autowired
	public ScheduleTask(HibernateAwareTask hibernateAwareTask, TaskRepository taskRepository,
			ConditionComparableRepository conditionComparableRepository, MailService mailService) {
		this.hibernateAwareTask = hibernateAwareTask;
		this.taskRepository = taskRepository;
		this.conditionComparableRepository = conditionComparableRepository;
		this.mailService = mailService;
	}

	@Override
	public void run() {
		logger.trace("Running Scheduler");
		hibernateAwareTask.execute(new HibernateRunnable() {
			public void run() {
				for (Task task : taskRepository.taskToExecute()) {
					executeTask(task);
				}

			}
		});
	}

	private void executeTask(Task task) {
		ConditionComparable detail = task.getDetail();
		detail.execute(mailService, conditionComparableRepository);
		task.setExecuted(true);
		taskRepository.update(task);
	}
}
