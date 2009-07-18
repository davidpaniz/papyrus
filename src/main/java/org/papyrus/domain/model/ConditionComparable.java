package org.papyrus.domain.model;

public interface ConditionComparable {
	long getId();

	void setTemplate(boolean template);

	ConditionComparable fromTemplate(ConditionComparable oldValue, ConditionComparable newValue);
}
