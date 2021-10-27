package repository;

import model.location.District;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistrictRepository extends CrudRepository<District, Long> {

	public List<District> selectByCityIds(Set<Long> cityIds);
}