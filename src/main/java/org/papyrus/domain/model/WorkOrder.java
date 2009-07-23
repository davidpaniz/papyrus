package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "Work_Order_Seq")
public class WorkOrder implements ConditionComparable {
	@Id
	@GeneratedValue(generator = "Work_Order_Seq", strategy = GenerationType.AUTO)
	private long id;
	private String description;

	@ManyToOne
	private Incident incident;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Incident getIncident() {
		return incident;
	}

}
