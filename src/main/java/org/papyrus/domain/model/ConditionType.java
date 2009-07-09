package org.papyrus.domain.model;

public enum ConditionType {
	INCIDENT(Incident.class);

	private final Class<? extends ConditionComparable> type;

	private ConditionType(Class<? extends ConditionComparable> type) {
		this.type = type;
	}

	public Class<? extends ConditionComparable> getType() {
		return type;
	}
}
