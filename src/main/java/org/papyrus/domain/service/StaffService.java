/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Staff;

/**
 * @author davidpaniz
 * 
 */
public interface StaffService {
	Staff deleteStaff(Staff staff) throws Exception;

	Staff saveStaff(Staff staff) throws Exception;

	List<Staff> listStaff() throws Exception;

}
