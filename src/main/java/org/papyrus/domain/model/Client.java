/**
 * 
 */
package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Entity that represents a Client
 * 
 * @author davidpaniz
 */
@Entity
public class Client extends User {

	public Client() {
		super();
		setRole(Role.CLIENT);
	}

	@ManyToOne
	private Company company;

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

}
