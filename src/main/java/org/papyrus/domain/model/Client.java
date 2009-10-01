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

	@ManyToOne
	private Priority priority;

	@ManyToOne
	private Company company;

	public Client() {
		super();
		setRole(Role.CLIENT);
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Priority getPriority() {
		return priority;
	}

}
