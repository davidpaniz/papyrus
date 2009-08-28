/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Client;

/**
 * @author davidpaniz
 * 
 */
public interface ClientService {
	Client deleteClient(Client client) throws Exception;

	Client saveClient(Client client) throws Exception;

	List<Client> listClient() throws Exception;

}
