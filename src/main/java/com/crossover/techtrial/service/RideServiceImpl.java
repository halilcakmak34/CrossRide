/**
 * 
 */
package com.crossover.techtrial.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.PropertyApp;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.model.TopDTO;
import com.crossover.techtrial.model.TopRide;
import com.crossover.techtrial.repositories.PropertyAppRepository;
import com.crossover.techtrial.repositories.RideRepository;
import com.crossover.techtrial.repositories.TopRideRepository;
import com.crossover.techtrial.util.DateUtil;
import com.mysql.fabric.xmlrpc.base.Array;

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

		/*
		 * Select r.driver,SUM(r. FROM Ride r Where r. Group By r.driver
		 * 
		 * 
		 */

		Map<Long, TopRide> topRideMap = new HashMap<>();
		List<TopRide> allTopList = (List<TopRide>) topRideRepository.findAll();
		for (TopRide topRide : allTopList) {
			topRideMap.put(topRide.getPerson().getId(), topRide);
		}

		PropertyApp latestCalculateDate = (PropertyApp) propertyAppRepository.findByKey("TOP_LATEST_CALCULATE_DATE");

		List<Ride> allRide;
		if (allTopList.size() != 0) {
			if (latestCalculateDate != null) {
				allRide = (List<Ride>) rideRepository.findByStartTime(latestCalculateDate.getValue());
			} else {
				allRide = (List<Ride>) rideRepository.findAll();
			}
		} else {
			allRide = (List<Ride>) rideRepository.findAll();
		}

		LocalDateTime startTime = null, endTime = null, rideStart, rideEnd;
		Duration time = null;

		for (int i = 0; i < allRide.size(); i++) {

			rideStart = DateUtil.getDateLocalTime(allRide.get(i).getStartTime());
			rideEnd = DateUtil.getDateLocalTime(allRide.get(i).getEndTime());

			if (rideStart.compareTo(startTimeFilter) < 0 || rideEnd.compareTo(endTimeFilter) > 0)
				continue;

			Long driverId = allRide.get(i).getDriver().getId();

			TopRide topRide = topRideMap.get(driverId);
			if (topRide != null) {
				topRide.setAverageDistance(topRide.getAverageDistance() + allRide.get(i).getDistance().doubleValue());
				startTime = DateUtil.getDateLocalTime(allRide.get(i).getStartTime());
				endTime = DateUtil.getDateLocalTime(allRide.get(i).getEndTime());
				time = Duration.between(startTime, endTime);
				if (topRide.getMaxRideDuration() < time.getSeconds()) {
					topRide.setMaxRideDuration(time.getSeconds());
				}
				topRide.setTotalRideDuration(topRide.getTotalRideDuration() + time.getSeconds());
				topRide.setRideCount(topRide.getRideCount() + 1);
			} else {
				startTime = DateUtil.getDateLocalTime(allRide.get(i).getStartTime());
				endTime = DateUtil.getDateLocalTime(allRide.get(i).getEndTime());
				time = Duration.between(startTime, endTime);
				topRide = new TopRide();
				topRide.setPerson(allRide.get(i).getDriver());
				topRide.setAverageDistance(allRide.get(i).getDistance().doubleValue());
				topRide.setTotalRideDuration(time.getSeconds());
				topRide.setMaxRideDuration(time.getSeconds());
				topRide.setRideCount(1);
			}
			topRideMap.put(driverId, topRide);
			topRideRepository.save(topRide);
		}

		if (latestCalculateDate == null) {
			latestCalculateDate = new PropertyApp();
			latestCalculateDate.setKey("TOP_LATEST_CALCULATE_DATE");
			latestCalculateDate.setValue(DateUtil.getStrFromLocalDateTime(LocalDateTime.now()));
			propertyAppRepository.save(latestCalculateDate);
		} else {
			latestCalculateDate.setValue(DateUtil.getStrFromLocalDateTime(LocalDateTime.now()));
		}

		List<TopRide> topRideList = new ArrayList<>(topRideMap.values());

		for (int i = 0; i < topRideList.size(); i++) {
			for (int j = 1; j < topRideList.size(); j++) {
				if (topRideList.get(i).getTotalRideDuration() < topRideList.get(j).getTotalRideDuration()) {
					TopRide tmp = topRideList.get(i);
					topRideList.set(i, topRideList.get(j));
					topRideList.set(j, tmp);
				}
			}
		}

		List<TopDriverDTO> result = new LinkedList<TopDriverDTO>();

		for (int i = 0; i < max.intValue(); i++) {
			TopDriverDTO topDriverDto = new TopDriverDTO();
			topDriverDto.setName(topRideList.get(i).getPerson().getName());
			topDriverDto.setEmail(topRideList.get(i).getPerson().getEmail());
			topDriverDto.setAverageDistance(topRideList.get(i).getAverageDistance()/topRideList.get(i).getRideCount());
			topDriverDto.setMaxRideDurationInSecods(topRideList.get(i).getMaxRideDuration());
			topDriverDto.setTotalRideDurationInSeconds(topRideList.get(i).getTotalRideDuration());
			result.add(topDriverDto);
		}

		return result;
	}

	@Override
	public List<TopDriverDTO> getTopDriverWithSQL(Long max, LocalDateTime startTimeFilter,
			LocalDateTime endTimeFilter) {
		List<TopDTO> tmp = rideRepository.findGetTopDriver(max, DateUtil.getStrFromLocalDateTime(startTimeFilter),
				DateUtil.getStrFromLocalDateTime(endTimeFilter));
		List<TopDriverDTO> result = new ArrayList<>();
		for (TopDTO t : tmp) {
			TopDriverDTO top = new TopDriverDTO();
			top.setName(t.getName());
			top.setEmail(t.getEmail());
			top.setAverageDistance(t.getAverageDistance());
			top.setTotalRideDurationInSeconds(t.getTotalRideDurationInSeconds().longValue());
			top.setMaxRideDurationInSecods(t.getMaxRideDurationInSecods().longValue());
			result.add(top);
		}

		return result;
	}

}
