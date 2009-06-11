package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Impact;
import org.papyrus.domain.repository.ImpactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "impactRepository")
public class ImpactDao implements ImpactRepository {

	private final HibernateTemplate template;

	@Autowired
	public ImpactDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Impact saveOrUpdate(Impact impact) {
		template.saveOrUpdate(impact);
		return impact;
	}

	public List<Impact> list() {
		List list = template.find("from Impact");
		return list;

	}

	public Impact delete(Impact impact) {
		template.delete(impact);
		return impact;
	}
}
