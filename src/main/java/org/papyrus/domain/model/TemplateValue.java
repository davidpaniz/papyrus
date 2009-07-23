package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "TemplateValue_Seq")
public class TemplateValue {
	@Id
	@GeneratedValue(generator = "TemplateValue_Seq", strategy = GenerationType.AUTO)
	private long id;

	private String field;
	private String value;

	//
	// @ManyToOne
	// private Action action;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	//
	// public Action getAction() {
	// return action;
	// }
	//
	// public void setAction(Action action) {
	// this.action = action;
	// }

}
