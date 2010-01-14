package org.papyrus.domain.repository;

import java.util.List;

import org.papyrus.domain.model.User;

/**
 * @author David Paniz
 * 
 *         interface responsible to manipulate and persist user
 */
public interface UserRepository {
	/**
	 * Save an user.
	 * 
	 * @return the user persisted with the id
	 * @param the
	 *            user that will be persisted
	 */
	User save(User user);

	User remove(User user);

	User login(User user);

	List<User> listStaffs();

	List<User> listClients();

	User loadUser(User user);
}
