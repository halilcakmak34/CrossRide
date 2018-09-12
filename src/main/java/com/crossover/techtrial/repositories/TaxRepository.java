/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.Driver;
import com.crossover.techtrial.model.Tax;

/**
 * @author halil cakmak
 */
@RestResource(exported=false)
public interface TaxRepository extends PagingAndSortingRepository<Tax, Long> {
  Optional<Tax> findById(Long id);
  
}
