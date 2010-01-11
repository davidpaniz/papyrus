/**
 * 
 */
package org.papyrus.domain.service;

import java.util.Date;
import java.util.List;

import org.papyrus.domain.model.Incident;
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

	public List<Incident> listIncident() {
		return repository.list();
	}

	public Incident deleteIncident(Incident incident) {
		incident.setUpdatedAt(new Date());
		return repository.delete(incident);
	}

	public Incident createIncident(Incident incident) {
		incident.fillIncidentDataOnCreate();
		businessRuleService.executeCreateCondition(incident);
		repository.saveDetails(incident);
		repository.saveOrUpdate(incident);
		return incident;
	}

	public Incident updateIncident(Incident incident) {
		businessRuleService.executeUpdateCondition(incident);
		incident.setUpdatedAt(new Date());
		repository.saveDetails(incident);
		Incident savedIncident = repository.saveOrUpdate(incident);
		return savedIncident;
	}
}
