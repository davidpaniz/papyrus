package org.papyrus.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.ConditionType;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist Business Rules
 */
public interface BusinessRuleRepository {
	/**
	 * Save an businessRule.
	 * 
	 * @return the businessRule persisted with the id
	 * @param the
	 *            businessRule that will be persisted
	 */
	BusinessRule saveOrUpdate(BusinessRule businessRule);

	List<BusinessRule> list();

	/**
	 * @param businessRule
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	BusinessRule delete(BusinessRule businessRule);

	List<BusinessRule> findCreateRules(ConditionType type);

	List<BusinessRule> findUpdateRules(ConditionType type);

	<T> T load(Class<T> type, Serializable id);

}
