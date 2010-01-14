package org.papyrus.domain.model.action;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.User;

@Entity
@SequenceGenerator(name = "User_Template_Seq")
public class UserActionValues implements UserAction {

	@Id
	@GeneratedValue(generator = "User_Template_Seq", strategy = GenerationType.AUTO)
	private long id;

	@Enumerated(EnumType.STRING)
	private UserActionValuesTemplate template;

	public UserActionValuesTemplate getTemplate() {
		return template;
	}

	public void setTemplate(UserActionValuesTemplate template) {
		this.template = template;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public User getUser(Incident incident, Task task) {
		return template.getUser(incident, task);
	}
}
