package org.papyrus.domain.exception;

import net.vidageek.mirror.exception.MirrorException;

public class BusinessRuleException extends Exception {
	private static final long serialVersionUID = 3220305802188122585L;

	public BusinessRuleException(MirrorException exception) {
		super(exception.getLocalizedMessage(), exception);
	}
}
