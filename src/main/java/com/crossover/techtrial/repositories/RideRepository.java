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

import com.crossover.techtrial.model.Ride;

/**
 * @author crossover
 *
 */
@RestResource(exported = false)
public interface RideRepository extends CrudRepository<Ride, Long> {

	Optional<Ride> findById(Long id);
	
	@Query("Select count(r) From Ride r where r.driver.id=:driverId")
	int totalRideByDriver(@Param("driverId") Long driverId);
	
	@Query("Select r From Ride r where date(r.startTime)>=date(:startTime)")
	List<Ride> findByStartTime(String startTime);
	
	@Query("Select r.driver.id, r.driver.name, r.driver.email, SUM(r.endTime-r.startTime), MAX(r.endTime-r.startTime), AVG(r.distance) From Ride r Group By r.driver.id ORDER By SUM(r.endTime-r.startTime) DESC")
	L
}
