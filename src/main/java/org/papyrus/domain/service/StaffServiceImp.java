/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Date;
import java.util.List;

import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.IncidentStatus;
import org.papyrus.domain.model.Staff;
import org.papyrus.domain.repository.StaffRepository;
import org.papyrus.infra.http.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "staffService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StaffServiceImp implements StaffService {
	private final StaffRepository repository;
	private final SessionManager sessionManager;

	@Autowired
	public StaffServiceImp(StaffRepository repository, SessionManager sessionManager) {
		this.repository = repository;
		this.sessionManager = sessionManager;
	}

	public List<Staff> listStaff() throws Exception {
		return repository.list();
	}

	public Staff deleteStaff(Staff staff) throws Exception {
		return repository.delete(staff);
	}

	public Staff saveStaff(Staff staff) throws Exception {
		return repository.saveOrUpdate(staff);
	}

	public List<Incident> listAllIncidents(IncidentStatus incidentStatus, Date inicialDate, Date endDate)
			throws Exception {
		List<Incident> inicidents = repository.listAllIncidents(incidentStatus, inicialDate, endDate);
		for (Incident incident : inicidents) {
			incident.setWorkOrders(null);
			incident.setAttachments(null);
		}
		return inicidents;
	}
}
