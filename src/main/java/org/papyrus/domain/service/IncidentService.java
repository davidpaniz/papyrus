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
	Incident deleteIncident(Incident incident) throws Exception;

	Incident saveIncident(Incident incident) throws Exception;

	List<Incident> listIncident() throws Exception;

}
