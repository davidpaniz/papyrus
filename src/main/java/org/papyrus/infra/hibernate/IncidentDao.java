package org.papyrus.infra.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.papyrus.domain.model.Detail;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.User;
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
		getSession().saveOrUpdate(incident);
		saveDetails(incident);
		return incident;
	}

	private Session getSession() {
		return template.getSessionFactory()
				.getCurrentSession();

	}

	private void saveDetails(Incident incident) {
		for (Detail detail : incident.getDetails()) {
			if (detail.getId() == 0) {
				detail.setDate(Calendar.getInstance());
				detail.setIncident(incident);
				getSession().saveOrUpdate(detail);
			}
		}
	}

	public List<Incident> list() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Incident.class);
		List<Incident> list = template.findByCriteria(criteria);

		for (Incident incident : list) {
			incident.setWorkOrders(null);
			incident.setAttachments(null);
			incident.setDetails(null);
		}

		return list;

	}

	public Incident delete(Incident incident) {
		template.delete(incident);
		return incident;
	}

	public List<Incident> listAllIncidents(IncidentStatus status, Date inicialDate, Date endDate) {
		Criteria criteria = createIncidentBaseCriteria(status, inicialDate, endDate);

		return criteria.list();
	}

	public List<Incident> listIncidentsAssignedTo(User user, IncidentStatus status, Date inicialDate, Date endDate) {
		Criteria criteria = createIncidentBaseCriteria(status, inicialDate, endDate);

		criteria.add(Restrictions.le("responsable", user));

		return criteria.list();
	}

	public List<Incident> listUserInicidents(User user, IncidentStatus status, Date inicialDate, Date endDate) {
		Criteria criteria = createIncidentBaseCriteria(status, inicialDate, endDate);

		criteria.add(Restrictions.eq("client", user));

		return criteria.list();
	}

	private Criteria createIncidentBaseCriteria(IncidentStatus status, Date inicialDate, Date endDate) {
		Criteria criteria = getSession().createCriteria(Incident.class);
		if (status != null) {
			criteria.add(Restrictions.eq("status", status));
		}
		if (inicialDate != null) {
			criteria.add(Restrictions.ge("openedDate", inicialDate));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("openedDate", endDate));
		}
		criteria.setFetchMode("details", FetchMode.JOIN)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

}
