package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
// FIXME configure prefix table name to all tables and remove this annotation
@Table(name = "Condition_tab")
@SequenceGenerator(name = "Condition_Seq")
public class Condition {

	@Id
	@GeneratedValue(generator = "Condition_Seq", strategy = GenerationType.AUTO)
	private Long id;

	private boolean onCreate;
	private boolean onUpdate;
	private boolean onDelete;

	@Enumerated(EnumType.STRING)
	private ConditionType type;

	private String expression1;
	private String expression2;

	@Enumerated(EnumType.STRING)
	private ConditionComparisonOperator comparisonOperator;

	@ManyToOne
	private BusinessRule businessRule;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConditionType getType() {
		return type;
	}

	public void setType(ConditionType type) {
		this.type = type;
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

	public void setBusinessRule(BusinessRule businessRule) {
		this.businessRule = businessRule;
	}

	public BusinessRule getBusinessRule() {
		return businessRule;
	}

}
