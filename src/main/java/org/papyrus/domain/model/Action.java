package org.papyrus.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.invoke.dsl.InvocationHandler;

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
	//
	// @Any(metaColumn = @Column(name = "detail_type"), fetch = FetchType.EAGER)
	// @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
	// @MetaValue(value = "INCIDENT", targetEntity = Incident.class),
	// @MetaValue(value = "WORK_ORDER", targetEntity = WorkOrder.class) })
	// @JoinColumn(name = "detail_id")
	// private ConditionComparable detail;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<TemplateValue> detail;

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

	public void setMethod(ActionMethod method) {
		this.method = method;
	}

	public ActionMethod getMethod() {
		return method;
	}

	public void setType(BusinessRuleType type) {
		this.type = type;
	}

	public BusinessRuleType getType() {
		return type;
	}

	public void setDetail(List<TemplateValue> detail) {
		this.detail = detail;
	}

	public List<TemplateValue> getDetail() {
		return detail;
	}

	public ConditionComparable detail(ConditionComparable oldValue) {
		ConditionComparable conditionComparable = new Mirror().on(type.getType())
				.invoke()
				.constructor()
				.withoutArgs();
		InvocationHandler<Object> handler = new Mirror().on(conditionComparable)
				.invoke();
		ExpressionResolver expressionResolver = new ExpressionResolver(oldValue, null);
		for (TemplateValue templateValue : detail) {
			handler.setterFor(templateValue.getField())
					.withValue(expressionResolver.valueOf(templateValue.getValue()));
		}

		return conditionComparable;
	}
}
