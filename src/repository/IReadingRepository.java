package repository;

import model.Reading;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReadingRepository extends CrudRepository<Reading, Long> {

	public List<Reading> selectByCityIds(Set<Long> cityIds);
}