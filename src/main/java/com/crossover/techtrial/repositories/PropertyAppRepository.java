/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.PropertyApp;

/**
 * @author halil cakmak
 */
@RestResource(exported=false)
public interface PropertyAppRepository extends PagingAndSortingRepository<PropertyApp, Long> {
  Optional<PropertyApp> findById(Long id);
  
  @Query("Select p From PropertyApp p Where p.key = :p_key")
  PropertyApp findByKey(@Param("p_key")String key);
}
