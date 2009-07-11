/**
 * 
 */
package org.papyrus.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity that represents an Impact
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Incident_Seq")
public class Incident implements ConditionComparable {
	@Id
	@GeneratedValue(generator = "Incident_Seq", strategy = GenerationType.AUTO)
	private long id;
	private String clientName;

	private String description;
	private String resolution;

	@ManyToOne
	private Impact impact;
	@ManyToOne
	private Urgency urgency;

	@Enumerated(EnumType.STRING)
	private IncidentStatus status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date openedDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date respondedDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;

	@OneToMany
	private List<WorkOrder> workOrders;
	@OneToMany
	private List<Attachment> attachments;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Impact getImpact() {
		return impact;
	}

	public void setImpact(Impact impact) {
		this.impact = impact;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public IncidentStatus getStatus() {
		return status;
	}

	public void setStatus(IncidentStatus status) {
		this.status = status;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public Date getRespondedDate() {
		return respondedDate;
	}

	public void setRespondedDate(Date respondedDate) {
		this.respondedDate = respondedDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<WorkOrder> getWorkOrders() {
		return workOrders;
	}

	public void setWorkOrders(List<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
