/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Date;
import java.util.List;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.User;

/**
 * @author davidpaniz
 * 
 */
public interface UserService {
	User login(User user);

	void logoutUser();

	User saveUser(User user);

	User deleteUser(User user);

	List<User> listAllStaffs();

	List<User> listAllClients();

	List<Incident> listMyIncidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate);

	List<Incident> listIncidentsAssignedToMe(IncidentStatus incidentStatus, Date inicialDate, Date endDate);
	// List<Incident> listAllIncidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate) throws Exception;
}
