package org.papyrus.domain.model;

public enum ConditionType {
	INCIDENT(Incident.class);

	private final Class<?> type;

	private ConditionType(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}
}
