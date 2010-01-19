package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.BusinessRule;

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

	List<BusinessRule> findCreateRules();

	List<BusinessRule> findUpdateRules();

	List<BusinessRule> findDeleteRules();

	BusinessRule load(Long id);

	void clearConditionsAndActionsOf(BusinessRule persistedBusinessRule);
}
