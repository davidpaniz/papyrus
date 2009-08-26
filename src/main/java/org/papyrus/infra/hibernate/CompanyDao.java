package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Company;
import org.papyrus.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "companyRepository")
public class CompanyDao implements CompanyRepository {

	private final HibernateTemplate template;

	@Autowired
	public CompanyDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Company saveOrUpdate(Company company) {
		template.saveOrUpdate(company);
		return company;
	}

	public List<Company> list() {
		List list = template.find("from Company");
		return list;

	}

	public Company delete(Company company) {
		template.delete(company);
		return company;
	}
}
