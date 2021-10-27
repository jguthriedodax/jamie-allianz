package main;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import model.Reading;
import model.Sensor;
import model.auth.User;
import model.location.City;
import repository.ICityRepository;
import repository.IDistrictRepository;
import repository.IReadingRepository;
import repository.ISensorRepository;
import repository.IUserRepository;

@Service
public class GeneratorService {

	@Autowired
    private ICityRepository cityRepository;

	@Autowired
    private IDistrictRepository districtRepository;

	@Autowired
    private ISensorRepository sensorRepository;

	@Autowired
    private IReadingRepository readingRepository;

	@Autowired
    private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Class that generates dummy data for testing
	 * @throws InterruptedException
	 */
	public GeneratorService() throws InterruptedException {

		ScheduledExecutorService execService = Executors.newScheduledThreadPool(2);

		execService.schedule(new Runnable() {
			public void run() {
				initUsers();
			}
		}, 1L, TimeUnit.SECONDS);

		// Create scheduled loop that runs every 5 minutes (generates dummy sensor data)
		execService.scheduleAtFixedRate(new Runnable() {
			public void run() {
				Iterable<Sensor> sensors = sensorRepository.findAll();

		  		sensors.forEach(sensor -> {
					List<Reading> readings = new ArrayList<>();

					readingRepository.findAll().forEach(readings::add);
	
					Reading latestReading =
						readings.stream()
						.filter(r -> r.getSensor().equals(sensor))
						.max(Comparator.comparing(Reading::getAtTime))
						.orElse(null);
	
					Reading newReading = new Reading();
					newReading.setAtTime(Instant.now());
					newReading.setReading(latestReading.getReading() + (int)(Math.random() * 10 - 5));
					newReading.setSensor(sensor);
	
					readingRepository.save(newReading);

					System.out.println(Instant.now() + " Uploaded new reading: SensorId: '" + sensor.getId() + "', Reading: '" + newReading.getReading() + "'");
		  		});
			}
		}, 5L, 20L, TimeUnit.SECONDS);
	}

	private void initUsers() {
		Optional<City> city = cityRepository.findById(1L);
		
		User user = new User();
		user.setUsername("barcelonaUser");
		user.setPassword(passwordEncoder.encode("barcelona123"));
		user.setCities(new HashSet<>(Arrays.asList(city.get())));

		userRepository.save(user);

		// User 2
		city = cityRepository.findById(2L);
		
		user = new User();
		user.setUsername("wienUser");
		user.setPassword(passwordEncoder.encode("wien123"));
		user.setCities(new HashSet<>(Arrays.asList(city.get())));

		userRepository.save(user);

		// User 3
		city = cityRepository.findById(3L);
		
		user = new User();
		user.setUsername("leedsUser");
		user.setPassword(passwordEncoder.encode("leeds123"));
		user.setCities(new HashSet<>(Arrays.asList(city.get())));

		userRepository.save(user);

	}
}
