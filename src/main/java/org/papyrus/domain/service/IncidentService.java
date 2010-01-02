/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.Staff;

/**
 * @author davidpaniz
 * 
 */
public interface IncidentService {
	Incident deleteIncident(Incident incident) throws Exception;

	Incident createIncident(Incident incident) throws Exception;

	Incident updateIncident(Incident incident) throws Exception;

	Incident closeIncident(Incident incident) throws Exception;

	Incident assignIncident(Incident incident, Staff staff) throws Exception;

	List<Incident> listIncident() throws Exception;

}
