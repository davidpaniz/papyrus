/**
 * 
 */
package org.papyrus.domain.service;

import java.util.List;

import org.papyrus.domain.model.Impact;

/**
 * @author davidpaniz
 * 
 */
public interface ImpactService {
	Impact deleteImpact(Impact impact) throws Exception;

	Impact saveImpact(Impact impact) throws Exception;

	List<Impact> listImpact() throws Exception;

}
