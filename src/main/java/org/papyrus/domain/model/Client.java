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
// @SequenceGenerator(name = "Client_Seq")
public class Client extends User {
	// @Id
	// @GeneratedValue(generator = "Client_Seq", strategy = GenerationType.AUTO)
	// private long id;
	// private String name;
	// private String email;

	@ManyToOne
	private Company company;

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

}
