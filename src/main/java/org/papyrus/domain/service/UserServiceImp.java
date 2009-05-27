/**
 * 
 */
package org.papyrus.domain.service;

import org.papyrus.domain.model.User;
import org.papyrus.domain.repository.UserRepository;
import org.papyrus.infra.http.DoesntRequiresLogin;
import org.papyrus.infra.http.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "userService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImp implements UserService {
	private final UserRepository userRepository;
	private final SessionManager sessionManager;

	@Autowired
	public UserServiceImp(UserRepository userRepository, SessionManager sessionManager) {
		this.userRepository = userRepository;
		this.sessionManager = sessionManager;
	}

	@DoesntRequiresLogin
	public User login(User user) {
		User login = userRepository.login(user);
		sessionManager.addUser(login);
		return login;
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public void logoutUser() {
		sessionManager.removeUser();
	}

}
