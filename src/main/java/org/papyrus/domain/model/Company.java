/**
 * 
 */
package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Entity that represents a Company
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Company_Seq")
public class Company {
	@Id
	@GeneratedValue(generator = "Company_Seq", strategy = GenerationType.AUTO)
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
