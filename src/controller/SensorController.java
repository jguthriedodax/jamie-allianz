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

import model.Sensor;
import repository.ISensorRepository;

@RestController
public class SensorController extends AuthController {

	@Autowired
    private ISensorRepository sensorRepository;

	@GetMapping("/sensor/all")
	public ResponseEntity<List<Sensor>> allSensors(HttpServletRequest request) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		List<Sensor> sensors = sensorRepository.selectByCityIds(authedCityIds);

		return new ResponseEntity<List<Sensor>>(sensors, HttpStatus.OK);
	}

	@GetMapping("/sensor/{id}")
	public ResponseEntity<Sensor> getSensor(HttpServletRequest request, @PathVariable Long id) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			Sensor sensor = sensorRepository.findById(id).get();

			if (authedCityIds.contains(sensor.getDistrict().getCity().getId()))
				return new ResponseEntity<Sensor>(sensor, HttpStatus.OK);
			else
				return new ResponseEntity<Sensor>(HttpStatus.UNAUTHORIZED);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Sensor>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Sensor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/sensor")
	public ResponseEntity<Sensor> addSensor(HttpServletRequest request, @RequestBody Sensor sensor) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			if (authedCityIds.contains(sensor.getDistrict().getCity().getId()))
				return new ResponseEntity<Sensor>(sensorRepository.save(sensor), HttpStatus.OK);
			else
				return new ResponseEntity<Sensor>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<Sensor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/sensor/{id}")
	public ResponseEntity<Sensor> deleteSensor(HttpServletRequest request, @PathVariable Long id) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			Sensor sensorToDelete = sensorRepository.findById(id).get();

			if (authedCityIds.contains(sensorToDelete.getDistrict().getCity().getId())) {
				sensorRepository.deleteById(id);
				return new ResponseEntity<Sensor>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Sensor>(HttpStatus.UNAUTHORIZED);
			}

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Sensor>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Sensor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
