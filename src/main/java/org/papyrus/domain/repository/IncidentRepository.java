package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Incident;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist incidents
 */
public interface IncidentRepository {
	/**
	 * Save an incident.
	 * 
	 * @return the incident persisted with the id
	 * @param the
	 *            incident that will be persisted
	 */
	Incident saveOrUpdate(Incident incident);

	List<Incident> list();

	/**
	 * @param incident
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	Incident delete(Incident incident);

}
