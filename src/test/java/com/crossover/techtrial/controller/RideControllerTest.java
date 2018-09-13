/**
 * 
 */
package com.crossover.techtrial.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.PersonRepository;
import com.crossover.techtrial.repositories.RideRepository;
import com.crossover.techtrial.repositories.TopRideRepository;
import com.crossover.techtrial.util.DateUtil;

/**
 * @author kshah
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RideControllerTest {

	MockMvc mockMvc;

	@Mock
	private RideController rideController;

	@Autowired
	private TestRestTemplate template;

	@Autowired
	RideRepository rideRepository;

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	TopRideRepository topRideRepository;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(rideController).build();
	}

	@Test
	public void testAddRide() throws Exception {

		HttpEntity<Object> person1 = getHttpEntity("{\"name\": \"testDriver\", \"email\": \"testDriver@gmail.com\","
				+ " \"registrationNumber\": \"DRIVER_41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }");
		ResponseEntity<Person> responsePerson1 = template.postForEntity("/api/person", person1, Person.class);

		Assert.assertEquals("testDriver", responsePerson1.getBody().getName());
		Assert.assertEquals(200, responsePerson1.getStatusCode().value());

		HttpEntity<Object> person2 = getHttpEntity("{\"name\": \"testRider\", \"email\": \"testRider@gmail.com\","
				+ " \"registrationNumber\": \"RIDER_41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }");
		ResponseEntity<Person> responsePerson2 = template.postForEntity("/api/person", person2, Person.class);
		Assert.assertEquals("testRider", responsePerson2.getBody().getName());
		Assert.assertEquals(200, responsePerson2.getStatusCode().value());

		String personJsonStr1 = "{\"id\":\"" + responsePerson1.getBody().getId()
				+ "\",\"name\": \"testDriver\", \"email\": \"testDriver@gmail.com\","
				+ " \"registrationNumber\": \"DRIVER_41DCT\"}";
		String personJsonStr2 = "{\"id\":\"" + responsePerson2.getBody().getId()
				+ "\",\"name\": \"testRider\", \"email\": \"testRider@gmail.com\","
				+ " \"registrationNumber\": \"RIDER_41DCT\"}";
		HttpEntity<Object> ride = getHttpEntity("{\"driver\":" + personJsonStr1 + ","
				+ "\"rider\":" + personJsonStr2 + ","
						+ "\"distance\":\"45\",\"startTime\":\"2018-08-11T10:00:00\","
						+ "\"endTime\":\"2018-08-12T10:39:00\"}");
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", ride, Ride.class);
		// Delete this user
		Assert.assertNotNull(response);
		rideRepository.deleteById(response.getBody().getId());
		Assert.assertNotNull(responsePerson1);
		personRepository.deleteById(responsePerson1.getBody().getId());
		Assert.assertNotNull(responsePerson2);
		personRepository.deleteById(responsePerson2.getBody().getId());
		Assert.assertEquals("RIDER_41DCT", response.getBody().getRider().getRegistrationNumber());
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void testGetRideById() throws Exception {
		String rideId = "3";
		ResponseEntity<Ride> response = template.getForEntity("/api/ride/" + rideId, Ride.class);
		Assert.assertEquals("3", response.getBody().getId().toString());
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void testTopDriver() throws Exception {
		Map<String, Object> parameter = new LinkedHashMap<String, Object>();
		parameter.put("max", "5");
		parameter.put("startTime", DateUtil.getDateLocalTime("2018-08-07T12:12:12"));
		parameter.put("endTime", DateUtil.getDateLocalTime("2018-08-17T12:12:12"));
		ResponseEntity<TopDriverDTO[]> response = template.getForEntity(
				"/api/top-rides?max={max}&startTime={startTime}&endTime={endTime}", TopDriverDTO[].class, parameter);
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

}
