/**
 * 
 */
package com.crossover.techtrial.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.service.PersonService;
import com.crossover.techtrial.service.RideService;
import com.crossover.techtrial.util.DateUtil;

/**
 * RideController for Ride related APIs.
 * 
 * @author crossover
 *
 */
@RestController
public class RideController {

	@Autowired
	RideService rideService;
	
	@Autowired
	PersonService personService;

	@PostMapping(path = "/api/ride")
	public ResponseEntity<Ride> createNewRide(@RequestBody Ride ride) {
		
		List<Person> personAll =  personService.getAll();
		
		if(ride.getDriver().equals(ride.getRider())) {
			throw new IllegalArgumentException("Driver and Rider cannot be same");
		}
		
		if(!personAll.contains(ride.getDriver()) || !personAll.contains(ride.getRider()))
		{
			throw new IllegalArgumentException("Driver or Rider Not Found");
		}	
		
		LocalDateTime rideStart = DateUtil.getDateLocalTime(ride.getStartTime()),
				rideEnd = DateUtil.getDateLocalTime(ride.getEndTime());
		
		if(rideStart.compareTo(rideEnd)>0) {
			throw new IllegalArgumentException("EndTime cannot be earlier than startTime.");
		}

		return ResponseEntity.ok(rideService.save(ride));
	}

	@GetMapping(path = "/api/ride/{ride-id}")
	public ResponseEntity<Ride> getRideById(@PathVariable(name = "ride-id", required = true) Long rideId) {
		Ride ride = rideService.findById(rideId);
		if (ride != null)
			return ResponseEntity.ok(ride);
		return ResponseEntity.notFound().build();
	}

	/**
	 * This API returns the top 5 drivers with their email,name, total minutes,
	 * maximum ride duration in minutes. Only rides that starts and ends within the
	 * mentioned durations should be counted. Any rides where either start or
	 * endtime is outside the search, should not be considered.
	 * 
	 * DONT CHANGE METHOD SIGNATURE AND RETURN TYPES
	 * 
	 * @return
	 */
	@GetMapping(path = "/api/top-rides")
	@ResponseBody
	public ResponseEntity<List<TopDriverDTO>> getTopDriver(@RequestParam(value = "max", defaultValue = "5") Long count,
			@RequestParam(value = "startTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
			@RequestParam(value = "endTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime) {

		if(startTime.compareTo(endTime)>0) {
			throw new IllegalArgumentException("EndTime cannot be earlier than startTime.");
		}
		
		List<TopDriverDTO> topDrivers = new ArrayList<TopDriverDTO>();

		topDrivers.addAll(rideService.getTopDriver(count, startTime, endTime));

		return ResponseEntity.ok(topDrivers);

	}

}
