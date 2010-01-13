package org.papyrus.domain.model.action;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.User;

public interface UserAction {
	User getUser(Incident incident, Task task);
}
