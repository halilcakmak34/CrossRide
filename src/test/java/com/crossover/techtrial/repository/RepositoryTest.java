package com.crossover.techtrial.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.techtrial.model.Driver;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.PropertyApp;
import com.crossover.techtrial.model.Tax;
import com.crossover.techtrial.model.TopRide;
import com.crossover.techtrial.model.Vehicle;
import com.crossover.techtrial.model.VehicleType;
import com.crossover.techtrial.repositories.DriverRepository;
import com.crossover.techtrial.repositories.PersonRepository;
import com.crossover.techtrial.repositories.PropertyAppRepository;
import com.crossover.techtrial.repositories.TaxRepository;
import com.crossover.techtrial.repositories.TopRideRepository;
import com.crossover.techtrial.repositories.VehicleRepository;
import com.crossover.techtrial.repositories.VehicleTypeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	TopRideRepository topRideRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	DriverRepository driverRepository;

	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	TaxRepository taxRepository;
	
	@Autowired
	VehicleTypeRepository vehicleTypeRepository;
	
	@Autowired
	PropertyAppRepository propertyAppRepository;

	@Test
	public void testAddTopRide() throws Exception {
		TopRide test = new TopRide();
		Person person = new Person();
		person.setName("TestName");
		person.setEmail("testtest34@gmail.com");
		person.setRegistrationNumber("test" + person.hashCode());
		person = personRepository.save(person);
		Assert.assertEquals("TestName", person.getName());
		test.setPerson(person);
		test.setMaxRideDuration(100l);
		test.setTotalRideDuration(2000l);
		test.setAverageDistance(87.00);
		test = topRideRepository.save(test);
		topRideRepository.deleteById(test.getId());
		personRepository.deleteById(person.getId());
		Assert.assertEquals("TestName", test.getPerson().getName());

	}

	@Test
	public void testAddDriver() {
		Driver driver = new Driver();
		Person person = new Person();
		person.setName("TestName");
		person.setEmail("testtest34@gmail.com");
		person.setRegistrationNumber("test" + person.hashCode());
		person = personRepository.save(person);
		Assert.assertNotNull(person);
		driver.setPerson(person);
		VehicleType vt = new VehicleType();
		vt.setVehicleType("Honda");
		vt = vehicleTypeRepository.save(vt);
		Assert.assertNotNull(vt);
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleType(vt);
		vehicle.setOwner(driver);
		vehicle = vehicleRepository.save(vehicle);
		Assert.assertNotNull(vehicle);
		Tax tx = new Tax();
		tx.setAmenable(driver);
		tx.setVehicle(vehicle);
		tx = taxRepository.save(tx);
		Assert.assertNotNull(tx);
		driver.setTaxInfo(tx);
		driver.setVehicle(vehicle);
		driver = driverRepository.save(driver);
		driverRepository.deleteById(driver.getId());
		vehicleRepository.deleteById(vehicle.getId());
		taxRepository.delete(tx);
		personRepository.delete(person);
		vehicleTypeRepository.delete(vt);
		Assert.assertNotNull(driver);
		Assert.assertEquals("TestName", driver.getPerson().getName());
		
	}
	
	@Test
    public void testAddPropertApp() {
		PropertyApp pa = new PropertyApp();
		pa.setKey("test");
		pa.setValue("testValue");
		PropertyApp saved = propertyAppRepository.save(pa);
		propertyAppRepository.deleteById(saved.getId());
		Assert.assertEquals("test", saved.getKey());
	}
	
	
}
