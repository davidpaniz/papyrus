package org.papyrus.domain.model;

import net.vidageek.mirror.dsl.Mirror;

public class ExpressionResolver {
	private final ConditionComparable oldValue;

	public ExpressionResolver(ConditionComparable oldValue, ConditionComparable newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	private final ConditionComparable newValue;

	public Object valueOf(String expression) {
		// TODO what should return if attribute does not exist?
		if (expression.indexOf("#") >= 0) {
			return getValue(oldValue, fieldInExpression(expression));
		} else if (expression.indexOf("$") >= 0) {
			return getValue(newValue, fieldInExpression(expression));
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
}
