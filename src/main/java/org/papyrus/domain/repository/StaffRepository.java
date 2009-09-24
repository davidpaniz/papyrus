package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.Staff;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist urgencies
 */
public interface StaffRepository {
	/**
	 * Save an staff.
	 * 
	 * @return the staff persisted with the id
	 * @param the
	 *            staff that will be persisted
	 */
	Staff saveOrUpdate(Staff staff);

	List<Staff> list();

	/**
	 * @param staff
	 * @return the same object if deleted or null if some problems ocurrer
	 */
	Staff delete(Staff staff);

}
