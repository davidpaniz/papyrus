/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Company;

/**
 * @author davidpaniz
 * 
 */
public interface CompanyService {
	Company deleteCompany(Company company) throws Exception;

	Company saveCompany(Company company) throws Exception;

	List<Company> listCompany() throws Exception;

}
