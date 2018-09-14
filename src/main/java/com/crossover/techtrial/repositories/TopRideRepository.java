/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.TopRide;

/**
 * @author halil cakmak
 */
@RestResource(exported=false)
public interface TopRideRepository extends PagingAndSortingRepository<TopRide, Long> {
  Optional<TopRide> findById(Long id);
  
  	 @Query("Select t From TopRide t where t.person.email=:email")
     TopRide findByTopRidePersonEmail(@Param("email") String email);
}
