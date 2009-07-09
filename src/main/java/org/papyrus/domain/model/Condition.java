package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;

import org.papyrus.domain.exception.BusinessRuleException;

@Entity
@SequenceGenerator(name = "Condition_Seq")
public class Condition {

	@Id
	@GeneratedValue(generator = "Condition_Seq", strategy = GenerationType.AUTO)
	private Long id;

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

	public void setBusinessRule(BusinessRule businessRule) {
		this.businessRule = businessRule;
	}

	public BusinessRule getBusinessRule() {
		return businessRule;
	}

	@SuppressWarnings("unchecked")
	public boolean test(ConditionComparable oldValue, ConditionComparable newValue) throws BusinessRuleException {
		Object firstParam = getParameter(oldValue, newValue, expression1);
		Object secondParam = getParameter(oldValue, newValue, expression2);

		if (this.comparisonOperator == ConditionComparisonOperator.EQ) {
			return firstParam.equals(secondParam);
		} else if (this.comparisonOperator == ConditionComparisonOperator.NEQ) {
			return !firstParam.equals(secondParam);
		} else if (this.comparisonOperator == ConditionComparisonOperator.GT) {
			return ((Comparable) firstParam).compareTo((secondParam)) > 0;
		} else if (this.comparisonOperator == ConditionComparisonOperator.LT) {
			return ((Comparable) firstParam).compareTo((secondParam)) < 0;
		}

		return false;
	}

	private Object getParameter(ConditionComparable oldValue, ConditionComparable newValue, String expression)
			throws BusinessRuleException {
		try {

			if (expression.indexOf("#") >= 0) {
				return getValue(oldValue, fieldInExpression(expression));
			} else if (expression.indexOf("$") >= 0) {
				return getValue(newValue, fieldInExpression(expression));
			}
		} catch (MirrorException e) {
			throw new BusinessRuleException(e);
		}
		return expression;
	}

	private String fieldInExpression(String expression) {
		return expression.substring(2, expression.length() - 1);
	}

	private Object getValue(Object param, String filedName) {
		return new Mirror().on(param)
				.invoke()
				.getterFor(filedName);
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
}