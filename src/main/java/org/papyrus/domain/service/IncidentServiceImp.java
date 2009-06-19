/**
 * 
 */
package org.papyrus.domain.service;

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

	@Autowired
	public IncidentServiceImp(IncidentRepository repository) {
		this.repository = repository;
	}

	public List<Incident> listIncident() throws Exception {
		return repository.list();
	}

	public Incident deleteIncident(Incident incident) throws Exception {
		return repository.delete(incident);
	}

	public Incident saveIncident(Incident incident) throws Exception {
		return repository.saveOrUpdate(incident);
	}
}
