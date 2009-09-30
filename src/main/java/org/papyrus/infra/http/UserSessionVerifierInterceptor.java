package org.papyrus.infra.http;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.papyrus.domain.exception.UserNotLoggedIn;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author davidpaniz
 * 
 */
public class UserSessionVerifierInterceptor {
	private static final Logger log = Logger.getLogger(UserSessionVerifierInterceptor.class);
	private final SessionManager sessionManager;

	@Autowired
	public UserSessionVerifierInterceptor(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public Object validate(ProceedingJoinPoint pjp) throws Throwable {
		Class<? extends Object> type = pjp.getTarget()
				.getClass();
		Signature signature = pjp.getSignature();

		if (sessionManager.isNotLogged()) {
			log.warn(String.format("Problema na sessão do usuário acessando o metodo %s do service %s",
					signature.getName(), type));
			throw new UserNotLoggedIn();
		}
		return pjp.proceed();
	}
}
