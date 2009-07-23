package org.papyrus.domain.model;


public enum BusinessRuleType {
	INCIDENT(Incident.class),
	WORK_ORDER(WorkOrder.class);

	private final Class<? extends ConditionComparable> type;

	private BusinessRuleType(Class<? extends ConditionComparable> type) {
		this.type = type;
	}

	public Class<? extends ConditionComparable> getType() {
		return type;
	}

	// TODO remove comment
	// public List<String> getAtrributes() {
	// List<String> result = new ArrayList<String>();
	// List<Method> setters = new Mirror().on(type)
	// .reflectAll()
	// .setters();
	// for (Method method : setters) {
	// result.add(method.getName());
	// }
	// return result;
	// }
}
