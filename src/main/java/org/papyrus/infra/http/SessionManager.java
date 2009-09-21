package org.papyrus.infra.http;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.papyrus.domain.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionManager implements Serializable {
	private static final long serialVersionUID = 8221501580592231395L;

	private final static String CURRENT_USER = "user";

	private HttpSession testSession;

	public void setSession(HttpSession session) {
		this.testSession = session;
	}

	public SessionManager() {
	}

	public boolean isNotLogged() {
		HttpSession session = getSession();
		try {
			return session.getAttribute(CURRENT_USER) == null;
		} catch (java.lang.IllegalStateException e) {
			return true;
		}
	}

	private HttpSession getSession() {
		if (this.testSession != null) {
			return this.testSession;
		}
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession(false);
		return session;
	}

	public void addUser(User user) {
		getSession().setAttribute(CURRENT_USER, user);
	}

	public void removeUser() {
		try {
			getSession().setAttribute(CURRENT_USER, null);
			getSession().invalidate();
		} catch (java.lang.IllegalStateException e) {
			return;
		}
	}

	public User getLoggedUser() {
		return (User) getSession().getAttribute(CURRENT_USER);
	}
}
