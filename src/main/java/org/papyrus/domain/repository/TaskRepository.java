package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Task;

public interface TaskRepository {
	Task saveTask(Task task);

	List<Task> taskToExecute();

	void update(Task task);
}
