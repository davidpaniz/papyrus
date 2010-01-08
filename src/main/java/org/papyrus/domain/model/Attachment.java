package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "Attachment_Seq")
public class Attachment {

	@Id
	@GeneratedValue(generator = "Attachment_Seq", strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Incident incident;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Incident getIncident() {
		return incident;
	}

}
