/**
 * 
 */
package org.papyrus.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity that represents a User
 * 
 * @author davidpaniz
 */
@Entity
@Table(name = "users")
@SequenceGenerator(name = "User_Seq")
public class User {
	@Id
	@GeneratedValue(generator = "User_Seq", strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String email;
	private String name;
	private String password;
	private boolean active;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne
	private Priority priority;

	@ManyToOne
	private Company company;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

}
