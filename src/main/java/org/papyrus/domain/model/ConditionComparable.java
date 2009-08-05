package org.papyrus.domain.model;

import org.papyrus.domain.repository.ConditionComparableRepository;
import org.papyrus.domain.service.MailService;

public interface ConditionComparable {
	long getId();

	void execute(MailService mailService, ConditionComparableRepository conditionComparableRepository);

	/**
	 * Must set field 'template' as true
	 */
	void asTemplate();

	/**
	 * Must set field 'template' as false
	 */
	void activeTemplate();
}
