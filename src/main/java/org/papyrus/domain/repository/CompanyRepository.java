package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Company;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist urgencies
 */
public interface CompanyRepository {
	/**
	 * 
	 * Save an company.
	 * 
	 * @return the company persisted with the id
	 * @param the
	 *            company that will be persisted
	 */
	Company saveOrUpdate(Company company);

	List<Company> list();

	/**
	 * @param company
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	Company delete(Company company);

}
