package org.papyrus.infra.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.Staff;
import org.papyrus.domain.model.User;
import org.papyrus.domain.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "staffRepository")
public class StaffDao implements StaffRepository {

	private final HibernateTemplate template;

	@Autowired
	public StaffDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Staff saveOrUpdate(Staff staff) {
		template.saveOrUpdate(staff);
		return staff;
	}

	public List<Staff> list() {
		List list = template.find("from Staff");
		return list;

	}

	public Staff findById(Integer id) {
		return (Staff) template.load(Staff.class, id);
	}

	public Staff delete(Staff staff) {
		template.delete(staff);
		return staff;
	}

	public List<Incident> listAllIncidents(IncidentStatus status, Date inicialDate, Date endDate) {
		Criteria criteria = template.getSessionFactory()
				.getCurrentSession()
				.createCriteria(Incident.class);
		if (status != null) {
			criteria.add(Restrictions.eq("status", status));
		}
		if (inicialDate != null) {
			criteria.add(Restrictions.ge("openedDate", inicialDate));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("openedDate", endDate));
		}

		return criteria.setFetchMode("details", FetchMode.JOIN)
				.list();
	}

	public List<Incident> listIncidentsAsseinedTo(User user, IncidentStatus status, Date inicialDate, Date endDate) {
		Criteria criteria = template.getSessionFactory()
				.getCurrentSession()
				.createCriteria(Incident.class);
		if (status != null) {
			criteria.add(Restrictions.eq("status", status));
		}
		if (inicialDate != null) {
			criteria.add(Restrictions.ge("openedDate", inicialDate));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("openedDate", endDate));
		}

		criteria.add(Restrictions.le("responsable", user));

		return criteria.setFetchMode("details", FetchMode.JOIN)
				.list();
	}
}
