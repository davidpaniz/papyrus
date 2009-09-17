package org.papyrus.domain.repository;

import java.util.Date;
import java.util.List;

import org.papyrus.domain.model.Client;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist urgencies
 */
public interface ClientRepository {
	/**
	 * 
	 * Save an client.
	 * 
	 * @return the client persisted with the id
	 * @param the
	 *            client that will be persisted
	 */
	Client saveOrUpdate(Client client);

	List<Client> list();

	/**
	 * @param client
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	Client delete(Client client);

	List<Incident> listMyInicidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate);

}
