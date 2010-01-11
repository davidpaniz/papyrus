package org.papyrus.domain.repository;

import org.papyrus.domain.model.action.Action;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist actions
 */
public interface ActionRepository {
	/**
	 * Save an action.
	 * 
	 * @return the action persisted with the id
	 * @param the
	 *            action that will be persisted
	 */
	Action save(Action action);

}