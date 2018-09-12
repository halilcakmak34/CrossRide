/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.Driver;

/**
 * @author halil cakmak
 */
@RestResource(exported=false)
public interface DriverRepository extends PagingAndSortingRepository<Driver, Long> {
  Optional<Driver> findById(Long id);
  
}
