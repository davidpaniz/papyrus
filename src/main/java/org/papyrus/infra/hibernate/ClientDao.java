package org.papyrus.infra.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.papyrus.domain.model.Client;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
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

	public List<Incident> listMyInicidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate) {
		Criteria criteria = template.getSessionFactory()
				.getCurrentSession()
				.createCriteria(Incident.class)
				.add(Restrictions.eq("status", incidentStatus));
		if (inicialDate != null) {
			criteria.add(Restrictions.ge("openedDate", inicialDate));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("openedDate", endDate));
		}

		return criteria.setFetchMode("detail", FetchMode.JOIN)
				.list();
	}
}
