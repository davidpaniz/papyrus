package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Entity that represents a Urgency
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Urgency_Seq")
public class Urgency {
	@Id
	@GeneratedValue(generator = "Urgency_Seq", strategy = GenerationType.AUTO)
	private long id;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
