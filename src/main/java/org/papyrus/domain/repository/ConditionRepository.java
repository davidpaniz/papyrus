package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Condition;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionType;

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

	List<Condition> list();

	/**
	 * @param condition
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	Condition delete(Condition condition);

	List<BusinessRule> findCreateRules(ConditionType incident);

}
