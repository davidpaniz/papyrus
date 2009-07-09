/**
 * 
 */
package org.papyrus.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private Long id;
	private String description;

	@Temporal(TemporalType.TIME)
	private Date duration;

	@Temporal(TemporalType.TIME)
	private Date response;

	@ManyToOne
	private Urgency urgency;

	@ManyToOne
	private Impact impact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public Date getDuration() {
		return duration;
	}

	public void setResponse(Date response) {
		this.response = response;
	}

	public Date getResponse() {
		return response;
	}
}
