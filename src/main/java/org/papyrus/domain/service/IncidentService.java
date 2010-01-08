/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Incident;

/**
 * @author davidpaniz
 * 
 */
public interface IncidentService {
	Incident deleteIncident(Incident incident);

	Incident createIncident(Incident incident);

	Incident updateIncident(Incident incident);

	List<Incident> listIncident();
}
