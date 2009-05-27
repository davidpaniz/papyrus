package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Urgency;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist urgencies
 */
public interface UrgencyRepository {
	/**
	 * Save an urgency.
	 * 
	 * @return the urgency persisted with the id
	 * @param the
	 *            urgency that will be persisted
	 */
	Urgency save(Urgency urgency);

	List<Urgency> list();

	/**
	 * @param id
	 * @return Urgency whith the specific id
	 */
	Urgency findById(Integer id);

	/**
	 * @param urgency
	 * @return the same object if deleted or null if same problems ocurrer
	 */
	Urgency delete(Urgency urgency);

}
