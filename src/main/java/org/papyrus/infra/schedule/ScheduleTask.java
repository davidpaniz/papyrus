package org.papyrus.infra.schedule;

import java.util.TimerTask;

import org.apache.log4j.Logger;

public class ScheduleTask extends TimerTask {
	private static final Logger logger = Logger.getLogger(ScheduleTask.class);

	public ScheduleTask() {
	}

	@Override
	public void run() {
		System.out.println("============================================================teste============================================================");
	}
}
