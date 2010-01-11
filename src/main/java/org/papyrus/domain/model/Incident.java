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

import org.joda.time.DateTime;

/**
 * Entity that represents an Impact
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Incident_Seq")
public class Incident {
	@Id
	@GeneratedValue(generator = "Incident_Seq", strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private User requester;

	@ManyToOne
	private User responsable;

	private String title;

	@ManyToOne
	private Priority priority;

	@Enumerated(EnumType.STRING)
	private IncidentStatus status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date openedDate = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date respondedDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;

	@OneToMany(mappedBy = "incident")
	private List<Detail> details;

	@OneToMany(mappedBy = "incident")
	private List<Attachment> attachments;

	@ManyToOne
	private Category category;

	private boolean template = false;

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

	public void activeTemplate() {
		this.template = false;
	}

	public void asTemplate() {
		this.template = true;
	}

	public boolean isTemplate() {
		return template;
	}

	public void setTemplate(boolean template) {
		this.template = template;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void calculateDueDate() {
		this.dueDate = this.priority == null ? null : new DateTime(this.openedDate).plusSeconds(
				this.priority.getDuration())
				.toDate();

	}

	public void fillIncidentDataOnCreate() {
		this.setOpenedDate(new Date());
		this.setUpdatedAt(new Date());
		this.setPriority(this.requester.getPriority());
		this.calculateDueDate();
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getResponsable() {
		return responsable;
	}

	public void setResponsable(User responsable) {
		this.responsable = responsable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
