package org.papyrus.domain.model.action;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.service.MailService;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

	public void setBusinessRule(BusinessRule businessRule) {
		this.businessRule = businessRule;
	}

	public BusinessRule getBusinessRule() {
		return businessRule;
	}

	public void execute(Incident incident, MailService mailService) {
	}
}
