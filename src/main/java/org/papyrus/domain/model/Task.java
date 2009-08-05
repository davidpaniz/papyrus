package org.papyrus.domain.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Entity
@SequenceGenerator(name = "Task_Seq")
public class Task {

	@Id
	@GeneratedValue(generator = "Task_Seq", strategy = GenerationType.AUTO)
	private long id;

	@Any(metaColumn = @Column(name = "detail_type"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "integer", metaType = "string", metaValues = {
			@MetaValue(value = "INCIDENT", targetEntity = Incident.class),
			@MetaValue(value = "WORK_ORDER", targetEntity = WorkOrder.class),
			@MetaValue(value = "MAIL_NOTIFICATION", targetEntity = MailNotification.class) })
	@JoinColumn(name = "detail_id")
	private ConditionComparable detail;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar duoDate;

	private boolean executed = false;

	public Task() {
	}

	public Task(ConditionComparable conditionComparable, Calendar duoDate) {
		detail = conditionComparable;
		this.duoDate = duoDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getDuoDate() {
		return duoDate;
	}

	public void setDuoDate(Calendar duoDate) {
		this.duoDate = duoDate;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public ConditionComparable getDetail() {
		return detail;
	}

	public void setDetail(ConditionComparable detail) {
		this.detail = detail;
	}

}