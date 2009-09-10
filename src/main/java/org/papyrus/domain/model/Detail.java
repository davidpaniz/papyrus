package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Entity that represents a Detail of an Impact
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Detail_Seq")
public class Detail {
	@Id
	@GeneratedValue(generator = "Detail_Seq", strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Incident incident;

	private String detail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
