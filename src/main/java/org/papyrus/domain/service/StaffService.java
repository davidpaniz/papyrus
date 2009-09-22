/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Date;
import java.util.List;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.Staff;

/**
 * @author davidpaniz
 * 
 */
public interface StaffService {
	Staff deleteStaff(Staff staff) throws Exception;

	Staff saveStaff(Staff staff) throws Exception;

	List<Staff> listStaff() throws Exception;

	List<Incident> listAllIncidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate) throws Exception;

	List<Incident> listIncidentsAssignedToMe(IncidentStatus incidentStatus, Date inicialDate, Date endDate)
			throws Exception;
}
