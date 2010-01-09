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
@Table(name = "conditions")
@SequenceGenerator(name = "Condition_Seq")
public class Condition {

	@Id
	@GeneratedValue(generator = "Condition_Seq", strategy = GenerationType.AUTO)
	private long id;

	private String expression1;
	private String expression2;

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

	public boolean test(ConditionComparable oldValue, ConditionComparable newValue) {
		ExpressionResolver resolver = new ExpressionResolver(oldValue, newValue);
		Object firstParam = resolver.valueOf(expression1);
		Object secondParam = resolver.valueOf(expression2);

		return this.comparisonOperator.compare(firstParam, secondParam);
	}

	public String getExpression1() {
		return expression1;
	}

	public void setExpression1(String expression1) {
		this.expression1 = expression1;
	}

	public String getExpression2() {
		return expression2;
	}

	public void setExpression2(String expression2) {
		this.expression2 = expression2;
	}

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