package org.papyrus.domain.model.condition;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionComparisonOperator;
import org.papyrus.domain.model.ConditionLogicalOperator;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.User;

@Entity
@Table(name = "conditions")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "Condition_Seq")
public abstract class Condition {

	@Id
	@GeneratedValue(generator = "Condition_Seq", strategy = GenerationType.AUTO)
	private long id;

	@Enumerated(EnumType.STRING)
	private ConditionComparisonOperator comparisonOperator;

	@Enumerated(EnumType.STRING)
	private ConditionLogicalOperator logicalOperator;

	@ManyToOne
	private BusinessRule businessRule;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setBusinessRule(BusinessRule businessRule) {
		this.businessRule = businessRule;
	}

	public BusinessRule getBusinessRule() {
		return businessRule;
	}

	public abstract boolean test(Incident incident, User loggedUser);

	public ConditionComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

	public void setComparisonOperator(ConditionComparisonOperator comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}

	public void setLogicalOperator(ConditionLogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public ConditionLogicalOperator getLogicalOperator() {
		return logicalOperator;
	}
}