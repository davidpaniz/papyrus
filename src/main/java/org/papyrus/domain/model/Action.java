package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "Action_Seq")
public class Action {

	@Id
	@GeneratedValue(generator = "Action_Seq", strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private BusinessRule businessRule;

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
}
