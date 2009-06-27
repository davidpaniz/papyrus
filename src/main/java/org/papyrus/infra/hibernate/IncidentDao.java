package org.papyrus.infra.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "incidentRepository")
public class IncidentDao implements IncidentRepository {

	private final HibernateTemplate template;

	@Autowired
	public IncidentDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Incident saveOrUpdate(Incident incident) {
		template.saveOrUpdate(incident);
		return incident;
	}

	public List<Incident> list() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Incident.class);
		List<Incident> list = template.findByCriteria(criteria);

		for (Incident incident : list) {
			incident.getWorkOrders()
					.size();
			incident.getAttachments()
					.size();
		}

		return list;

	}

	public Incident delete(Incident incident) {
		template.delete(incident);
		return incident;
	}
}