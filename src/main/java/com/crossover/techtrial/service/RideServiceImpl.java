/**
 * 
 */
package com.crossover.techtrial.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.model.TopRide;
import com.crossover.techtrial.repositories.PropertyAppRepository;
import com.crossover.techtrial.repositories.RideRepository;
import com.crossover.techtrial.repositories.TopRideRepository;
import com.crossover.techtrial.util.DateUtil;

/**
 * @author crossover
 *
 */
@Service
public class RideServiceImpl implements RideService {

	@Autowired
	RideRepository rideRepository;
	
	@Autowired
	TopRideRepository topRideRepository;
	
	@Autowired
	PropertyAppRepository propertyAppRepository;

	public Ride save(Ride ride) {
		return rideRepository.save(ride);
	}

	public Ride findById(Long rideId) {
		Optional<Ride> optionalRide = rideRepository.findById(rideId);
		if (optionalRide.isPresent()) {
			return optionalRide.get();
		} else
			return null;
	}

	@Override
	public List<TopDriverDTO> getTopDriver(Long max, LocalDateTime startTimeFilter, LocalDateTime endTimeFilter) {

		List<Ride> allRide = (List<Ride>) rideRepository.findAll();
		LocalDateTime startTime = null, endTime = null, rideStart, rideEnd;
		Duration time = null;

		Map<Long, TopDriverDTO> driverMap = new HashMap<Long, TopDriverDTO>();

		for (int i = 0; i < allRide.size(); i++) {

			rideStart = DateUtil.getDateLocalTime(allRide.get(i).getStartTime());
			rideEnd = DateUtil.getDateLocalTime(allRide.get(i).getEndTime());

			if (rideStart.compareTo(startTimeFilter) < 0 || rideEnd.compareTo(endTimeFilter) > 0)
				continue;

			Long driverId = allRide.get(i).getDriver().getId();

			TopDriverDTO topDriverDto = driverMap.get(driverId);
			if (topDriverDto != null) {
				topDriverDto.setName(allRide.get(i).getDriver().getName());
				topDriverDto.setEmail(allRide.get(i).getDriver().getEmail());
				topDriverDto.setAverageDistance(topDriverDto.getAverageDistance() + allRide.get(i).getDistance().doubleValue());
				startTime = DateUtil.getDateLocalTime(allRide.get(i).getStartTime());
				endTime = DateUtil.getDateLocalTime(allRide.get(i).getEndTime());
				time = Duration.between(startTime, endTime);
				if (topDriverDto.getMaxRideDurationInSecods() < time.getSeconds()) {
					topDriverDto.setMaxRideDurationInSecods(time.getSeconds());
				}
				topDriverDto.setTotalRideDurationInSeconds(
						topDriverDto.getTotalRideDurationInSeconds() + time.getSeconds());
				driverMap.put(driverId, topDriverDto);
			} else {
				startTime = DateUtil.getDateLocalTime(allRide.get(i).getStartTime());
				endTime = DateUtil.getDateLocalTime(allRide.get(i).getEndTime());
				time = Duration.between(startTime, endTime);
				topDriverDto = new TopDriverDTO();
				topDriverDto.setName(allRide.get(i).getDriver().getName());
				topDriverDto.setEmail(allRide.get(i).getDriver().getEmail());
				topDriverDto.setAverageDistance(allRide.get(i).getDistance().doubleValue());
				topDriverDto.setMaxRideDurationInSecods(time.getSeconds());
				topDriverDto.setTotalRideDurationInSeconds(time.getSeconds());
				driverMap.put(driverId, topDriverDto);
			}

		}
		
		
		for(Long driver : driverMap.keySet()) {
			int totalRideCount = rideRepository.totalRideByDriver(driver);
			driverMap.get(driver).setAverageDistance(driverMap.get(driver).getAverageDistance()/totalRideCount);
			TopRide topRide = new TopRide();
			topRide.setRideCount(totalRideCount);
			topRide.setAverageDistance(driverMap.get(driver).getAverageDistance());
			topRideRepository.
		}

		List<TopDriverDTO> result = new ArrayList<>(driverMap.values());

		for (int i = 0; i < result.size(); i++) {
			for (int j = 1; j < result.size(); j++) {

				if (result.get(i).getTotalRideDurationInSeconds() < result.get(j).getTotalRideDurationInSeconds()) {
					TopDriverDTO tmp = result.get(i);
					result.set(i, result.get(j));
					result.set(j, tmp);
				}
			}
		}
		return result.subList(0, max.intValue());
	}

}
