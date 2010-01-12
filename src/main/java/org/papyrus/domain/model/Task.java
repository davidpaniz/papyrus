package org.papyrus.domain.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "Task_Seq")
public class Task {

	@Id
	@GeneratedValue(generator = "Task_Seq", strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Incident incident;

	@ManyToOne
	private BusinessRule businessRule;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar scheduledDate;

	private boolean executed = false;

	public Task(Incident incident, BusinessRule businessRule, Calendar scheduledDate) {
		this.businessRule = businessRule;
		this.incident = incident;
		this.scheduledDate = scheduledDate;
	}

	@Deprecated
	public Task() {
	}

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

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setScheduledDate(Calendar scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public Calendar getScheduledDate() {
		return scheduledDate;
	}

	public void setBusinessRule(BusinessRule businessRule) {
		this.businessRule = businessRule;
	}

	public BusinessRule getBusinessRule() {
		return businessRule;
	}
}