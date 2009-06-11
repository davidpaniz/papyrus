/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Priority;

/**
 * @author davidpaniz
 * 
 */
public interface PriorityService {
	Priority deletePriority(Priority priority) throws Exception;

	Priority savePriority(Priority priority) throws Exception;

	List<Priority> listPriority() throws Exception;

}
