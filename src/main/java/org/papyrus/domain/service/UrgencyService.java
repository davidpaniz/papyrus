/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Urgency;

/**
 * @author davidpaniz
 * 
 */
public interface UrgencyService {
	Urgency deleteUrgency(Urgency urgency) throws Exception;

	Urgency saveUrgency(Urgency urgency) throws Exception;

	Urgency findById(Integer id) throws Exception;

	List<Urgency> listUrgency() throws Exception;

}
