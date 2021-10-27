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

import model.location.District;
import repository.IDistrictRepository;

@RestController
public class DistrictController extends AuthController {

	@Autowired
    private IDistrictRepository districtRepository;

	@GetMapping("/district/all")
	public ResponseEntity<List<District>> allDistricts(HttpServletRequest request) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		List<District> districts = districtRepository.selectByCityIds(authedCityIds);

		return new ResponseEntity<List<District>>(districts, HttpStatus.OK);
	}

	@GetMapping("/district/{id}")
	public ResponseEntity<District> getDistrict(HttpServletRequest request, @PathVariable Long id) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			District district = districtRepository.findById(id).get();

			if (authedCityIds.contains(district.getCity().getId()))
				return new ResponseEntity<District>(district, HttpStatus.OK);
			else
				return new ResponseEntity<District>(HttpStatus.UNAUTHORIZED);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/district")
	public ResponseEntity<District> addDistrict(HttpServletRequest request, @RequestBody District district) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			if (authedCityIds.contains(district.getCity().getId()))
				return new ResponseEntity<District>(districtRepository.save(district), HttpStatus.OK);
			else
				return new ResponseEntity<District>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/district/{id}")
	public ResponseEntity<District> deleteDistrict(HttpServletRequest request, @PathVariable Long id) {
		Set<Long> authedCityIds = getAuthedCityIds(request);

		try {
			District districtToDelete = districtRepository.findById(id).get();

			if (authedCityIds.contains(districtToDelete.getCity().getId())) {
				districtRepository.deleteById(id);
				return new ResponseEntity<District>(HttpStatus.OK);
			} else {
				return new ResponseEntity<District>(HttpStatus.UNAUTHORIZED);
			}

		} catch (NoSuchElementException e) {
			return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
