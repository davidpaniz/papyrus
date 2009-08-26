/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Company;
import org.papyrus.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "companyService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CompanyServiceImp implements CompanyService {
	private final CompanyRepository repository;

	@Autowired
	public CompanyServiceImp(CompanyRepository repository) {
		this.repository = repository;
	}

	public List<Company> listCompany() throws Exception {
		return repository.list();
	}

	public Company deleteCompany(Company company) throws Exception {
		return repository.delete(company);
	}

	public Company saveCompany(Company company) throws Exception {
		return repository.saveOrUpdate(company);
	}
}
