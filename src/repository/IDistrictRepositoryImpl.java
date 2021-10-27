package repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import model.location.District;

@Component
public class IDistrictRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<District> selectByCityIds(Set<Long> cityIds) {
		String hql = "FROM District d WHERE d.city.id IN :cityIds";
		TypedQuery<District> query = entityManager.createQuery(hql, District.class);
		query.setParameter("cityIds", cityIds);

		return query.getResultList();
	}
}