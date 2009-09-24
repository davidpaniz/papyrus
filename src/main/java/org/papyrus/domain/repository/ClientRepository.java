package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Client;

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

}
