/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.TopRide;

/**
 * @author halil cakmak
 */
@RestResource(exported=false)
public interface TopRideRepository extends PagingAndSortingRepository<TopRide, Long> {
  Optional<TopRide> findById(Long id);
  
}
