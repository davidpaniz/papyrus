package org.papyrus.domain.repository;

import org.papyrus.domain.model.condition.Condition;

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

}
