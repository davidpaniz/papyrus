package org.papyrus.infra.schedule;

import java.util.TimerTask;

import org.apache.log4j.Logger;

public class ScheduleTask extends TimerTask {
	private static final Logger logger = Logger.getLogger(ScheduleTask.class);

	// @Autowired
	public ScheduleTask() {
	}

	@Override
	public void run() {
		logger.trace("Running Scheduler");
	}
}
