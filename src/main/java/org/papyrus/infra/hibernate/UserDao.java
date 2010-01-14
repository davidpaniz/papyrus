package org.papyrus.infra.hibernate;

import java.util.List;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.papyrus.domain.model.Role;
import org.papyrus.domain.model.User;
import org.papyrus.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
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
		return (User) getSession().createQuery("from User where email = :email and password = :password")
				.setParameter("email", user.getEmail())
				.setParameter("password", user.getPassword())
				.uniqueResult();
	}

	private Session getSession() {
		Session currentSession = template.getSessionFactory()
				.getCurrentSession();
		return currentSession;
	}

	public List<User> listClients() {
		return getSession().createCriteria(User.class)
				.add(Restrictions.eq("role", Role.CLIENT))
				.list();
	}

	public List<User> listStaffs() {
		return getSession().createCriteria(User.class)
				.add(Restrictions.eq("role", Role.STAFF))
				.list();
	}

	public User remove(User user) {
		user.setActive(false);
		template.save(user);
		return user;
	}

	public User loadUser(User user) {
		return (User) getSession().load(User.class, user.getId());
	}
}
