package repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import model.Reading;

@Component
public class IReadingRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Reading> selectByCityIds(Set<Long> cityIds) {
		String hql = "FROM Reading r WHERE r.sensor.district.city.id IN :cityIds";
		TypedQuery<Reading> query = entityManager.createQuery(hql, Reading.class);
		query.setParameter("cityIds", cityIds);

		return query.getResultList();
	}
}