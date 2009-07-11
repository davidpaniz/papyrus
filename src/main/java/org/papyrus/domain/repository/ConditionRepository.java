package org.papyrus.domain.repository;

import org.papyrus.domain.model.Condition;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist conditions
 */
public interface ConditionRepository {
	/**
	 * Save a condition.
	 * 
	 * @return the condition persisted with the id
	 * @param the
	 *            condition that will be persisted
	 */
	Condition saveOrUpdate(Condition condition);

	/**
	 * @param condition
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	Condition delete(Condition condition);

}
