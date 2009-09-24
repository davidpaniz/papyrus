package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Client;
import org.papyrus.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "clientRepository")
public class ClientDao implements ClientRepository {

	private final HibernateTemplate template;

	@Autowired
	public ClientDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Client saveOrUpdate(Client client) {
		template.saveOrUpdate(client);
		return client;
	}

	public List<Client> list() {
		List list = template.find("from Client");
		return list;

	}

	public Client delete(Client client) {
		template.delete(client);
		return client;
	}
}
