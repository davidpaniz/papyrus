/**
 * 
 */
package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Entity that represents an Impact
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Priory_Seq")
public class Priority {
	@Id
	@GeneratedValue(generator = "Priory_Seq", strategy = GenerationType.AUTO)
	private long id;
	private String description;

	@ManyToOne
	private Urgency urgency;

	@ManyToOne
	private Impact impact;

	private long duration;
	private long response;

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

	public void setImpact(Impact impact) {
		this.impact = impact;
	}

	public Impact getImpact() {
		return impact;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getResponse() {
		return response;
	}

	public void setResponse(long response) {
		this.response = response;
	}

}
