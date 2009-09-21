/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Date;
import java.util.List;

import org.papyrus.domain.model.Client;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.repository.ClientRepository;
import org.papyrus.infra.http.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "clientService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ClientServiceImp implements ClientService {
	private final ClientRepository repository;
	private final SessionManager sessionManager;

	@Autowired
	public ClientServiceImp(ClientRepository repository, SessionManager sessionManager) {
		this.repository = repository;
		this.sessionManager = sessionManager;
	}

	public List<Client> listClient() throws Exception {
		return repository.list();
	}

	public Client deleteClient(Client client) throws Exception {
		return repository.delete(client);
	}

	public Client saveClient(Client client) throws Exception {
		return repository.saveOrUpdate(client);
	}

	public List<Incident> listMyIncidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate)
			throws Exception {
		List<Incident> inicidents = repository.listUserInicidents(sessionManager.getLoggedUser(), incidentStatus,
				inicialDate, endDate);
		for (Incident incident : inicidents) {
			incident.setWorkOrders(null);
			incident.setAttachments(null);
		}
		return inicidents;
	}
}
