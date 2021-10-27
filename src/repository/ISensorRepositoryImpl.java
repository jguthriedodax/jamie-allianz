package repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import model.Sensor;

@Component
public class ISensorRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Sensor> selectByCityIds(Set<Long> cityIds) {
		String hql = "FROM Sensor s WHERE s.district.city.id IN :cityIds";
		TypedQuery<Sensor> query = entityManager.createQuery(hql, Sensor.class);
		query.setParameter("cityIds", cityIds);

		return query.getResultList();
	}
}