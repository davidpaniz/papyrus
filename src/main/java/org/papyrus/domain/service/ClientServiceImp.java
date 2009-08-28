/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Client;
import org.papyrus.domain.repository.ClientRepository;
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

	@Autowired
	public ClientServiceImp(ClientRepository repository) {
		this.repository = repository;
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
}
