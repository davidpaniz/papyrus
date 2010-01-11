package org.papyrus.domain.repository;

import org.papyrus.domain.model.Incident;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist ConditionComparables
 */
public interface ConditionComparableRepository {

	/**
	 * Save a conditionComparable as a template (template field set true).
	 * 
	 * @return the conditionComparable persisted with the id
	 * @param the
	 *            conditionComparable that will be persisted as a template
	 */
	Incident saveTemplate(Incident conditionComparable);

	/**
	 * Update the conditionComparable setting as a non template (template field set false).
	 * 
	 * @param the
	 *            conditionComparable that will be activated
	 */
	void activeTemplate(Incident conditionComparable);

	Incident load(Incident detail);
}
