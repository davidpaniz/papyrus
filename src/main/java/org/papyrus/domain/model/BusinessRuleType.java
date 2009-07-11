package org.papyrus.domain.model;

public enum BusinessRuleType {
	INCIDENT(Incident.class);

	private final Class<? extends ConditionComparable> type;

	private BusinessRuleType(Class<? extends ConditionComparable> type) {
		this.type = type;
	}

	public Class<? extends ConditionComparable> getType() {
		return type;
	}
}
