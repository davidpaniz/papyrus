package org.papyrus.infra.schedule;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.action.Action;
import org.papyrus.domain.repository.IncidentRepository;
import org.papyrus.domain.repository.TaskRepository;
import org.papyrus.domain.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTask extends TimerTask {
	private static final Logger logger = Logger.getLogger(ScheduleTask.class);
	private final MailService mailService;
	private final TaskRepository taskRepository;
	private final IncidentRepository incidentRepository;
	private final HibernateAwareTask hibernateAwareTask;

	@Autowired
	public ScheduleTask(HibernateAwareTask hibernateAwareTask, TaskRepository taskRepository,
			IncidentRepository incidentRepository, MailService mailService) {
		this.hibernateAwareTask = hibernateAwareTask;
		this.taskRepository = taskRepository;
		this.incidentRepository = incidentRepository;
		this.mailService = mailService;
	}

	@Override
	public void run() {
		logger.trace("Running Scheduler");
		try {
			hibernateAwareTask.execute(new HibernateRunnable() {
				public void run() {
					for (Task task : taskRepository.taskToExecute()) {
						executeTask(task);
					}

				}
			});
		} catch (Exception e) {
			logger.error("Error on Scheduler", e);
		}
	}

	private void executeTask(Task task) {
		Incident incident = task.getIncident();
		for (Action action : task.getBusinessRule()
				.getActions()) {
			action.execute(incident, mailService, task);
			incidentRepository.saveOrUpdate(incident);
		}
		task.setExecuted(true);
		taskRepository.update(task);
	}
}
