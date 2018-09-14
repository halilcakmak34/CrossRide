/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.model.TopDriverModel;





/**
 * @author crossover
 *
 */
@RestResource(exported = false)
public interface RideRepository extends CrudRepository<Ride, Long> {

	Optional<Ride> findById(Long id);
	
	@Query("Select count(r) From Ride r where r.driver.id=:driverId")
	int totalRideByDriver(@Param("driverId") Long driverId);
	
	@Query("Select r From Ride r where r.startTime>=:startTime")
	List<Ride> findByStartTime(@Param("startTime")String startTime);
	@Query(value="SELECT P.name as name, P.email as email, SUM(TIME_TO_SEC(TIMEDIFF(R.end_time, R.start_time))) as totalRideDurationInSeconds,MAX(TIME_TO_SEC(TIMEDIFF(R.end_time, R.start_time))) as maxRideDurationInSecods, AVG(R.distance) as averageDistance FROM ride R INNER JOIN person P ON R.driver_id=P.id WHERE R.start_time>=:startTime AND R.end_time<=:endTime GROUP BY P.name, P.email ORDER BY totalRideDurationInSeconds DESC LIMIT :max",
			nativeQuery = true)
	List<TopDriverModel> findGetTopDriver(@Param("max") Long max,@Param("startTime") String startTime,@Param("endTime") String endTime);
	
}
