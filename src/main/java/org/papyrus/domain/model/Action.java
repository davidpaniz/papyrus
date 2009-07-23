package org.papyrus.domain.model;

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

@Entity
@SequenceGenerator(name = "Action_Seq")
public abstract class Action {

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

	@OneToMany(mappedBy = "action")
	private List<TemplateValue> values;

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

	//
	// public void setDetails(ConditionComparable comparable) {
	// // FIXME fix this method. Wainting finish frontend
	// this.values = new ArrayList<TemplateValue>();
	// List<Field> fields = new Mirror().on(comparable.getClass())
	// .reflectAll()
	// .fields();
	// GetterHandler getterHandler = new Mirror().on(comparable)
	// .get();
	// for (Field field : fields) {
	// new TemplateValue(field.getName(), getterHandler.field(field));
	// }
	// }

	public void setValues(List<TemplateValue> values) {
		this.values = values;
	}

	public List<TemplateValue> getValues() {
		return values;
	}
}
