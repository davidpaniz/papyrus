/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Date;
import java.util.List;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.User;
import org.papyrus.domain.repository.IncidentRepository;
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
	private final IncidentRepository incidentRepository;

	@Autowired
	public UserServiceImp(UserRepository userRepository, SessionManager sessionManager,
			IncidentRepository incidentRepository) {
		this.userRepository = userRepository;
		this.sessionManager = sessionManager;
		this.incidentRepository = incidentRepository;
	}

	@DoesntRequiresLogin
	public User login(User user) {
		User login = userRepository.login(user);
		sessionManager.addUser(login);
		return login;
	}

	public User saveUser(User user) {
		if (user.getId() == 0) {
			return createUser(user);
		} else {
			return updateUser(user);
		}

	}

	private User updateUser(User user) {
		User loadedUser = userRepository.loadUser(user);
		loadedUser.setCompany(user.getCompany());
		loadedUser.setEmail(user.getEmail());
		loadedUser.setName(user.getEmail());
		loadedUser.setPriority(user.getPriority());
		return userRepository.save(loadedUser);
	}

	private User createUser(User user) {
		user.setPassword("Papyrus321");
		return userRepository.save(user);
	}

	@DoesntRequiresLogin
	public void logoutUser() {
		sessionManager.removeUser();
	}

	public List<Incident> listMyIncidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate) {
		List<Incident> inicidents = incidentRepository.listUserInicidents(sessionManager.getLoggedUser(),
				incidentStatus, inicialDate, endDate);
		for (Incident incident : inicidents) {
			incident.setAttachments(null);
		}
		return inicidents;
	}

	public List<Incident> listAllIncidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate)
			throws Exception {
		List<Incident> inicidents = incidentRepository.listAllIncidents(incidentStatus, inicialDate, endDate);
		for (Incident incident : inicidents) {
			incident.setAttachments(null);
		}
		return inicidents;
	}

	public List<Incident> listIncidentsAssignedToMe(IncidentStatus incidentStatus, Date inicialDate, Date endDate) {
		List<Incident> inicidents = incidentRepository.listIncidentsAssignedTo(sessionManager.getLoggedUser(),
				incidentStatus, inicialDate, endDate);
		for (Incident incident : inicidents) {
			incident.setAttachments(null);
		}
		return inicidents;
	}

	public User deleteUser(User user) {
		return userRepository.remove(user);
	}

	public List<User> listAllClients() {
		return userRepository.listClients();
	}

	public List<User> listAllStaffs() {
		return userRepository.listStaffs();
	}
}
