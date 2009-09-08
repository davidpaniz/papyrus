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
 * Entity that represents a Staff
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Staff_Seq")
public class Staff {
	@Id
	@GeneratedValue(generator = "Staff_Seq", strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String email;

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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
