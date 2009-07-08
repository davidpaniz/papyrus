package org.papyrus.domain.model;

public interface ConditionComparable<T extends Object> {
	public boolean equal(T param2);

	public boolean greater(T param2);
}
