package org.papyrus.domain.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	private boolean onCreate;
	private boolean onUpdate;
	private boolean onDelete;

	@Enumerated(EnumType.STRING)
	private BusinessRuleType type;

	@OneToMany(mappedBy = "businessRule")
	private List<Condition> conditions;

	@OneToMany(mappedBy = "businessRule")
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

	public void setType(BusinessRuleType type) {
		this.type = type;
	}

	public BusinessRuleType getType() {
		return type;
	}

	/**
	 * 
	 * @param oldValue
	 * @param newValue
	 * @return the boolean result of execution condition chain
	 */
	public boolean shouldExecute(ConditionComparable oldValue, ConditionComparable newValue) {
		Condition before = null;
		// keep the result of execution of the first condition
		boolean result = conditions.get(0)
				.test(oldValue, newValue);

		// iteration start on 1 couse the first one was evaluated before looping
		for (int i = 1; i < conditions.size(); i++) {
			Condition condition = conditions.get(i);
			// evaluate the current condition
			boolean conditionResult = condition.test(oldValue, newValue);

			// next result will be the current result comparated (by the logical operator of the last condition) with
			// the result of the current condition and stored as the new result
			result = before.getLogicalOperator()
					.compare(result, conditionResult);
			before = condition;
		}

		// return the stored result
		return result;
	}

	public Calendar calculateDate() {
		// TODO should implement method when implements tasks scheduling
		return Calendar.getInstance();
	}

	public BusinessRule copy() {
		BusinessRule businessRule = new BusinessRule();
		businessRule.actions = this.actions;
		businessRule.conditions = this.conditions;
		businessRule.description = this.description;
		businessRule.enabled = this.enabled;
		businessRule.id = this.id;
		businessRule.name = this.name;
		businessRule.onCreate = this.onCreate;
		businessRule.onDelete = this.onDelete;
		businessRule.onUpdate = this.onUpdate;
		businessRule.type = this.type;

		return businessRule;
	}
}
