/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Impact;
import org.papyrus.domain.repository.ImpactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author davidpaniz
 * 
 */
@Service(value = "impactService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ImpactServiceImp implements ImpactService {
	private final ImpactRepository repository;

	@Autowired
	public ImpactServiceImp(ImpactRepository repository) {
		this.repository = repository;
	}

	public List<Impact> listImpact() throws Exception {
		return repository.list();
	}

	public Impact deleteImpact(Impact impact) throws Exception {
		return repository.delete(impact);
	}

	public Impact saveImpact(Impact impact) throws Exception {
		return repository.saveOrUpdate(impact);
	}
}
