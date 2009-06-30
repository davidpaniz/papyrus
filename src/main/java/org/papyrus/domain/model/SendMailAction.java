package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class SendMailAction extends Action {

	@Override
	public void doAction(ActionParameter parameter) {
		System.out.println(((SendMailParameter) parameter).getMessage());
	}

	public class SendMailParameter implements ActionParameter {
		private final String message;

		public SendMailParameter(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

	}
}
