/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Urgency;
import org.papyrus.domain.repository.UrgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "urgencyService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UrgencyServiceImp implements UrgencyService {
	private final UrgencyRepository repository;

	@Autowired
	public UrgencyServiceImp(UrgencyRepository repository) {
		this.repository = repository;
	}

	public List<Urgency> listUrgency() throws Exception {
		return repository.list();
	}

	public Urgency deleteUrgency(Urgency urgency) throws Exception {
		return repository.delete(urgency);
	}

	public Urgency saveUrgency(Urgency urgency) throws Exception {
		return repository.saveOrUpdate(urgency);
	}
}
