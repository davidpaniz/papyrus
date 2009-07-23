package org.papyrus.infra.http;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.papyrus.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionManager implements Serializable {
	private static final long serialVersionUID = 8221501580592231395L;

	private final static String CURRENT_USER = "user";
	private HttpSession session;

	// @Autowired
	// public SessionManager(HttpSession session) {
	// this.session = session;
	// }

	@Autowired
	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Deprecated
	public SessionManager() {
	}

	public boolean isNotLogged() {
		try {
			return this.session.getAttribute(CURRENT_USER) == null;
		} catch (java.lang.IllegalStateException e) {
			return true;
		}
	}

	public void addUser(User user) {
		this.session.setAttribute(CURRENT_USER, user);
	}

	public void removeUser() {
		try {
			this.session.setAttribute(CURRENT_USER, null);
			this.session.invalidate();
		} catch (java.lang.IllegalStateException e) {
			return;
		}
	}
}
