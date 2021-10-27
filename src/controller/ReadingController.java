package controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Reading;
import repository.IReadingRepository;

@RestController
public class ReadingController extends AuthController {

	@Autowired
    private IReadingRepository readingRepository;

	@GetMapping("/reading/all")
	public ResponseEntity<List<Reading>> allReadings(HttpServletRequest request) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		List<Reading> readings = readingRepository.selectByCityIds(authedCityIds);

		return new ResponseEntity<List<Reading>>(readings, HttpStatus.OK);
	}

	@GetMapping("/reading/{id}")
	public ResponseEntity<Reading> getReading(HttpServletRequest request, @PathVariable Long id) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			Reading reading = readingRepository.findById(id).get();

			if (authedCityIds.contains(reading.getSensor().getDistrict().getCity().getId()))
				return new ResponseEntity<Reading>(reading, HttpStatus.OK);
			else
				return new ResponseEntity<Reading>(HttpStatus.UNAUTHORIZED);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Reading>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Reading>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/reading")
	public ResponseEntity<Reading> addReading(HttpServletRequest request, @RequestBody Reading reading) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			if (authedCityIds.contains(reading.getSensor().getDistrict().getCity().getId()))
				return new ResponseEntity<Reading>(readingRepository.save(reading), HttpStatus.OK);
			else
				return new ResponseEntity<Reading>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<Reading>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/reading/{id}")
	public ResponseEntity<Reading> deleteReading(HttpServletRequest request, @PathVariable Long id) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			Reading readingToDelete = readingRepository.findById(id).get();

			if (authedCityIds.contains(readingToDelete.getSensor().getDistrict().getCity().getId())) {
				readingRepository.deleteById(id);
				return new ResponseEntity<Reading>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Reading>(HttpStatus.UNAUTHORIZED);
			}

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Reading>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Reading>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
