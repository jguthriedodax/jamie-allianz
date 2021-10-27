package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import model.auth.User;

@Component
public class IUserRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public User selectByUsername(String username) {
		String hql = "FROM User u WHERE u.username = :username";
		TypedQuery<User> query = entityManager.createQuery(hql, User.class);
		query.setParameter("username", username);

		return query.getSingleResult();
	}
}