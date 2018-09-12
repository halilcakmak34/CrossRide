/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.VehicleType;

/**
 * @author halil cakmak
 */
@RestResource(exported=false)
public interface VehicleTypeRepository extends PagingAndSortingRepository<VehicleType, Long> {
  Optional<VehicleType> findById(Long id);
  
}
