package org.papyrus.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "Business_Rule_Seq")
public class BusinessRule {

	@Id
	@GeneratedValue(generator = "Business_Rule_Seq", strategy = GenerationType.AUTO)
	private long id;

	private boolean enabled;
	private String description;
	private String name;
	@OneToMany(mappedBy = "businessRule")
	private List<ActionCondition> conditions;
	@OneToMany
	private List<Action> actions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ActionCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<ActionCondition> conditions) {
		this.conditions = conditions;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

}
