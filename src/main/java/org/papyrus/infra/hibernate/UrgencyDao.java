package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Urgency;
import org.papyrus.domain.repository.UrgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "urgencyRepository")
public class UrgencyDao implements UrgencyRepository {

	private final HibernateTemplate template;

	@Autowired
	public UrgencyDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Urgency save(Urgency urgency) {
		template.saveOrUpdate(urgency);
		return urgency;
	}

	public List<Urgency> list() {
		List list = template.find("from Urgency");
		return list;

	}

	public Urgency findById(Integer id) {
		return (Urgency) template.load(Urgency.class, id);
	}

	public Urgency delete(Urgency urgency) {
		template.delete(urgency);
		return urgency;
	}
}
