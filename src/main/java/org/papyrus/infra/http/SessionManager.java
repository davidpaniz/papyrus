package org.papyrus.infra.http;

import javax.servlet.http.HttpSession;

import org.papyrus.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionManager {
	private final static String CURRENT_USER = "user";
	private HttpSession session;

	@Autowired
	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Deprecated
	public SessionManager() {
	}

	public boolean isNotLogged() {
		return this.session.getAttribute(CURRENT_USER) == null;
	}

	public void addUser(User user) {
		this.session.setAttribute(CURRENT_USER, user);
	}

	public void removeUser() {
		this.session.setAttribute(CURRENT_USER, null);
	}

	//
	// public void removeCurrentUser() {
	// this.requestsession
	// .removeAttribute(CURRENT_USER);
	// }
	//
	// public User getCurrentUser() {
	// return (User) this.requestsession
	// .getAttribute(CURRENT_USER);
	// }

}
