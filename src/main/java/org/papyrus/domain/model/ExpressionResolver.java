package org.papyrus.domain.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;

import org.apache.log4j.Logger;

public class ExpressionResolver {
	private final Object oldValue;
	private final Object newValue;

	private static final Logger logger = Logger.getLogger(ExpressionResolver.class);

	public ExpressionResolver(Object oldValue, Object newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@SuppressWarnings("unchecked")
	public Object valueOf(String expression) {
		Object evaluatedExpression = evaluateExpression(expression);
		if (evaluatedExpression instanceof Enum) {
			return ((Enum) evaluatedExpression).name();
		}
		return evaluatedExpression;
	}

	private Object evaluateExpression(String expression) {
		if (hasTextInExpression(expression)) {
			return findExpressionInText(expression);
		}
		return resolveExpression(expression);
	}

	private String findExpressionInText(String fullText) {
		Pattern pattern = Pattern.compile("(#|\\$)\\{.*?\\}");
		Matcher matcher = pattern.matcher(fullText);
		StringBuilder builder = new StringBuilder();
		int lastIndexOfExpression = 0;
		while (matcher.find()) {
			String foundExpression = matcher.group();
			logger.debug(String.format("Found Expression: %s", foundExpression));
			int newIndexOf = fullText.indexOf(foundExpression);

			builder.append(fullText.substring(lastIndexOfExpression, newIndexOf))
					.append(resolveExpression(foundExpression));

			lastIndexOfExpression = newIndexOf + foundExpression.length();
		}

		builder.append(fullText.substring(lastIndexOfExpression));
		return builder.toString();
	}

	private boolean hasTextInExpression(String expression) {
		// LineStart(# or $){ anything different of ('{') than }LineEnd
		if (expression.matches("^(#|\\$)\\{(^\\{)*\\}$")) {
			return false;
		} else {
			return true;
		}
	}

	private Object resolveExpression(String expression) {
		try {
			if (expression.indexOf("#") >= 0) {
				return getValue(oldValue, removeBraces(expression));
			} else if (expression.indexOf("$") >= 0) {
				return getValue(newValue, removeBraces(expression));
			}
		} catch (MirrorException e) {
			logger.warn(String.format("Expression %s is invalid", expression));
		}
		return expression;
	}

	private String removeBraces(String expression) {
		return expression.substring(2, expression.length() - 1);
	}

	private Object getValue(Object param, String filedName) {
		if (filedName.contains(".")) {
			int indexOfDot = filedName.indexOf(".");
			return getValue(new Mirror().on(param)
					.invoke()
					.getterFor(filedName.substring(0, indexOfDot)), filedName.substring(indexOfDot + 1));
		} else {
			return new Mirror().on(param)
					.invoke()
					.getterFor(filedName);
		}

	}
}
