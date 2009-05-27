package org.papyrus.infra.hibernate;

import org.papyrus.domain.model.User;
import org.papyrus.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepository")
public class UserDao implements UserRepository {

	private final HibernateTemplate template;

	@Autowired
	public UserDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public User save(User user) {
		template.saveOrUpdate(user);
		return user;
	}

	public User login(User user) {
		return (User) template.getSessionFactory()
				.getCurrentSession()
				.createQuery("from User where email = :email and password = :password")
				.setParameter("email", user.getEmail())
				.setParameter("password", user.getPassword())
				.uniqueResult();
	}
}
