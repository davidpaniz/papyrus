/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Date;
import java.util.List;

import org.papyrus.domain.model.Client;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;

/**
 * @author davidpaniz
 * 
 */
public interface ClientService {
	Client deleteClient(Client client) throws Exception;

	Client saveClient(Client client) throws Exception;

	List<Client> listClient() throws Exception;

	List<Incident> listMyIncidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate) throws Exception;

}
