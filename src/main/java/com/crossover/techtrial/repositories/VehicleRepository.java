/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.Vehicle;

/**
 * @author halil cakmak
 */
@RestResource(exported=false)
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {
  Optional<Vehicle> findById(Long id);
  
}
