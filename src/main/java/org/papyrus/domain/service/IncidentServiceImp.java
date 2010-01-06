/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.BusinessRuleType;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.Staff;
import org.papyrus.domain.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "incidentService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IncidentServiceImp implements IncidentService {
	private final IncidentRepository repository;
	private final BusinessRuleService businessRuleService;

	@Autowired
	public IncidentServiceImp(BusinessRuleService businessRuleService, IncidentRepository repository) {
		this.repository = repository;
		this.businessRuleService = businessRuleService;
	}

	public List<Incident> listIncident() throws Exception {
		return repository.list();
	}

	public Incident deleteIncident(Incident incident) throws Exception {
		return repository.delete(incident);
	}

	public Incident createIncident(Incident incident) throws Exception {
		businessRuleService.executeCreateCondition(BusinessRuleType.INCIDENT, incident);
		incident.fillIncidentDataOnCreate();
		repository.saveOrUpdate(incident);
		return incident;
	}

	public Incident updateIncident(Incident incident) throws Exception {
		businessRuleService.executeUpdateCondition(BusinessRuleType.INCIDENT, incident);
		repository.saveDetails(incident);
		Incident savedIncident = repository.saveOrUpdate(incident);
		// savedIncident.getDetails();
		return savedIncident;
	}

	public Incident assignIncident(Incident incident, Staff staff) throws Exception {
		incident.assignTo(staff);
		businessRuleService.executeUpdateCondition(BusinessRuleType.INCIDENT, incident);
		return repository.saveOrUpdate(incident);
	}

	public Incident closeIncident(Incident incident) throws Exception {
		incident.close();
		businessRuleService.executeUpdateCondition(BusinessRuleType.INCIDENT, incident);
		return repository.saveOrUpdate(incident);
	}

	// public Incident loadIncident(Incident incident) throws Exception {
	// return repository.load(incident);
	// }
}
