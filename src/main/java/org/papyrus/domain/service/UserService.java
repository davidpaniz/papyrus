/**
 * 
 */
package org.papyrus.domain.service;

import org.papyrus.domain.model.User;

/**
 * @author davidpaniz
 * 
 */
public interface UserService {
	public User login(User user);

	public void logoutUser();

	public User saveUser(User user);

}
