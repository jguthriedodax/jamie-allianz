package repository;

import model.Sensor;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISensorRepository extends CrudRepository<Sensor, Long> {

	public List<Sensor> selectByCityIds(Set<Long> cityIds);
}