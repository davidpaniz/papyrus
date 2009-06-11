/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Priority;
import org.papyrus.domain.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "priorityService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PriorityServiceImp implements PriorityService {
	private final PriorityRepository repository;

	@Autowired
	public PriorityServiceImp(PriorityRepository repository) {
		this.repository = repository;
	}

	public List<Priority> listPriority() throws Exception {
		return repository.list();
	}

	public Priority deletePriority(Priority priority) throws Exception {
		return repository.delete(priority);
	}

	public Priority savePriority(Priority priority) throws Exception {
		return repository.saveOrUpdate(priority);
	}
}
