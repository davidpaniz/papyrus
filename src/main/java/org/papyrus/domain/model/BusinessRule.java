package org.papyrus.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.papyrus.domain.exception.BusinessRuleException;

@Entity
@SequenceGenerator(name = "Business_Rule_Seq")
public class BusinessRule {

	@Id
	@GeneratedValue(generator = "Business_Rule_Seq", strategy = GenerationType.AUTO)
	private Long id;

	private boolean enabled;
	private String description;
	private String name;

	private boolean onCreate;
	private boolean onUpdate;
	private boolean onDelete;

	@Enumerated(EnumType.STRING)
	private ConditionType type;

	@OneToMany(mappedBy = "businessRule")
	private List<Condition> conditions;

	@OneToMany(mappedBy = "businessRule")
	private List<Action> actions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public boolean getOnCreate() {
		return onCreate;
	}

	public void setOnCreate(boolean onCreate) {
		this.onCreate = onCreate;
	}

	public boolean getOnUpdate() {
		return onUpdate;
	}

	public void setOnUpdate(boolean onUpdate) {
		this.onUpdate = onUpdate;
	}

	public boolean getOnDelete() {
		return onDelete;
	}

	public void setOnDelete(boolean onDelete) {
		this.onDelete = onDelete;
	}

	public void setType(ConditionType type) {
		this.type = type;
	}

	public ConditionType getType() {
		return type;
	}

	public boolean shouldExecute(ConditionComparable oldValue, ConditionComparable newValue)
			throws BusinessRuleException {
		boolean result = false;

		for (Condition condition : this.conditions) {
			result = result && condition.test(oldValue, newValue);
		}
		return result;
	}

}
