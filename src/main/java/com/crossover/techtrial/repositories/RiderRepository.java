/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.Rider;

/**
 * @author halil cakmak
 */
@RestResource(exported=false)
public interface RiderRepository extends PagingAndSortingRepository<Rider, Long> {
  Optional<Rider> findById(Long id);
  
}
