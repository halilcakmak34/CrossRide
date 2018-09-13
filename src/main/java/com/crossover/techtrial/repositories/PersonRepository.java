/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.Person;

/**
 * Person repository for basic operations on Person entity.
 * @author crossover
 */
@RestResource(exported=false)
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
  Optional<Person> findById(Long id);
  
  	@Query("select p from Person p where p.name = :name")
	List<Person> findByName(@Param("name") String name);
  	
  	@Query("SELECT p.registrationNumber FROM Person p WHERE p.registrationNumber=:registrationNumber")
  	List<String>  findByRegistrationNumber(@Param("registrationNumber") String registrationNumber);
}
