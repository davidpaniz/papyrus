package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Impact;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist impacts
 */
public interface ImpactRepository {
	/**
	 * Save an impact.
	 * 
	 * @return the impact persisted with the id
	 * @param the
	 *            impact that will be persisted
	 */
	Impact saveOrUpdate(Impact impact);

	List<Impact> list();

	/**
	 * @param impact
	 * @return the same object if deleted or null if same problems ocurrer
	 */
	Impact delete(Impact impact);

}
