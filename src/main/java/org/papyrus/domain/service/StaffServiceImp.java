/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Staff;
import org.papyrus.domain.repository.StaffRepository;
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

	@Autowired
	public StaffServiceImp(StaffRepository repository) {
		this.repository = repository;
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
}
