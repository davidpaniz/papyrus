package org.papyrus.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Entity
@SequenceGenerator(name = "Action_Seq")
public class Action {

	@Id
	@GeneratedValue(generator = "Action_Seq", strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private BusinessRule businessRule;

	@Enumerated(EnumType.STRING)
	private ActionMethod method;

	@Enumerated(EnumType.STRING)
	private BusinessRuleType type;

	@Any(metaColumn = @Column(name = "detail_type"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "integer", metaType = "string", metaValues = {
			@MetaValue(value = "INCIDENT", targetEntity = Incident.class),
			@MetaValue(value = "WORK_ORDER", targetEntity = WorkOrder.class) })
	@JoinColumn(name = "detail_id")
	private ConditionComparable detail;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void doAction() {
		System.out.println("Executing action");
	}

	public void setBusinessRule(BusinessRule businessRule) {
		this.businessRule = businessRule;
	}

	public BusinessRule getBusinessRule() {
		return businessRule;
	}

	public void setDetail(ConditionComparable detail) {
		if (type != null) {
			if (detail.getClass()
					.equals(this.type.getType())) {
				this.detail = detail;
			} else {
				throw new IllegalStateException();
			}
		}
	}

	public ConditionComparable getDetail() {
		return detail;
	}

	public void setMethod(ActionMethod method) {
		this.method = method;
	}

	public ActionMethod getMethod() {
		return method;
	}

	public void setType(BusinessRuleType type) {
		if (detail != null) {
			if (detail.getClass()
					.equals(type.getType())) {
				this.type = type;
			} else {
				throw new IllegalStateException();
			}
		}
	}

	public BusinessRuleType getType() {
		return type;
	}
}
