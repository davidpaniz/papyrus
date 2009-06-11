package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Priority;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist priorities
 */
public interface PriorityRepository {
	/**
	 * Save an priority.
	 * 
	 * @return the priority persisted with the id
	 * @param the
	 *            impact that will be persisted
	 */
	Priority saveOrUpdate(Priority priority);

	List<Priority> list();

	/**
	 * @param priority
	 * @return the same object if deleted or null if same problems ocurrer
	 */
	Priority delete(Priority priority);

}
