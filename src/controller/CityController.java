package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.location.City;
import repository.ICityRepository;

@RestController
public class CityController extends AuthController {

	@Autowired
    private ICityRepository cityRepository;

	@GetMapping("/city/all")
	public ResponseEntity<List<City>> allCities(HttpServletRequest request) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		List<City> cities = new ArrayList<>();
		cityRepository.findAll().forEach(cities::add);

		// Only return cities contained in the authedCities list
		return new ResponseEntity<List<City>>(
			cities.stream().filter(c -> authedCityIds.contains(c.getId())).collect(Collectors.toList()),
			HttpStatus.OK);
	}

	@GetMapping("/city/{id}")
	public ResponseEntity<City> getCity(HttpServletRequest request, @PathVariable Long id) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			City city = cityRepository.findById(id).get();

			if (authedCityIds.contains(city.getId()))
				return new ResponseEntity<City>(city, HttpStatus.OK);
			else
				return new ResponseEntity<City>(HttpStatus.UNAUTHORIZED);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<City>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
