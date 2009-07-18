package org.papyrus.domain.repository;

import org.papyrus.domain.model.ConditionComparable;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist conditionComparables
 */
public interface ConditionComparableRepository {
	/**
	 * Save a conditionComparable.
	 * 
	 * @return the conditionComparable persisted with the id
	 * @param the
	 *            conditionComparable that will be persisted
	 */
	ConditionComparable save(ConditionComparable conditionComparable);

}
