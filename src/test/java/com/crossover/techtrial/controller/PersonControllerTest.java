/**
 * 
 */
package com.crossover.techtrial.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.crossover.techtrial.repositories.PersonRepository;
import com.crossover.techtrial.util.DateUtil;

/**
 * @author kshah
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	MockMvc mockMvc;

	@Mock
	private PersonController personController;

	@Autowired
	private TestRestTemplate template;

	@Autowired
	PersonRepository personRepository;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}

	@Test
	public void testGetPersonByIdService() throws Exception {
		HttpEntity<Object> person = getHttpEntity("{\"name\": \"testtest\", \"email\": \"test34346363@gmail.com\","
				+ " \"registrationNumber\": \"41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }");
		ResponseEntity<Person> response = template.postForEntity("/api/person", person, Person.class);
		Assert.assertNotNull(response);
		String personId = response.getBody().getId().toString();
		response = template.getForEntity("/api/person/" + personId, Person.class);
		personRepository.deleteById(Long.parseLong(personId));
		Assert.assertEquals("testtest", response.getBody().getName());
		Assert.assertEquals("test34346363@gmail.com", response.getBody().getEmail());
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void testGetPersonAllService() throws Exception {
		HttpEntity<Object> person = getHttpEntity("{\"name\": \"testtest\", \"email\": \"test34346363@gmail.com\","
				+ " \"registrationNumber\": \"41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }");
		ResponseEntity<Person> response = template.postForEntity("/api/person", person, Person.class);
		Assert.assertNotNull(response);
		ResponseEntity<Person[]> responsePersonArr = template.getForEntity("/api/person/", Person[].class);

		List<Person> list = Arrays.asList(responsePersonArr.getBody());
		personRepository.deleteById(response.getBody().getId());
		Assert.assertTrue(list.contains(response.getBody()));
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void testPanelShouldBeRegistered() throws Exception {
		HttpEntity<Object> person = getHttpEntity("{\"name\": \"test 1\", \"email\": \"test10000000000001@gmail.com\","
				+ " \"registrationNumber\": \"41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }");
		ResponseEntity<Person> response = template.postForEntity("/api/person", person, Person.class);
		// Delete this user
		Assert.assertNotNull(response);
		personRepository.deleteById(response.getBody().getId());
		Assert.assertEquals("test 1", response.getBody().getName());
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	@Test
	public void testUniqueRegistrationService() throws Exception {
		HttpEntity<Object> person1 = getHttpEntity("{\"name\": \"testtest1\", \"email\": \"test10000000000001@gmail.com\","
				+ " \"registrationNumber\": \"41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }");
		HttpEntity<Object> person2 = getHttpEntity("{\"name\": \"testtest2\", \"email\": \"test10000000000001@gmail.com\","
				+ " \"registrationNumber\": \"41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }");
		ResponseEntity<Person> response1 = template.postForEntity("/api/person", person1, Person.class);
		Assert.assertNotNull(response1);
		Assert.assertEquals(200, response1.getStatusCode().value());
		ResponseEntity<Person> response2 = template.postForEntity("/api/person", person2, Person.class);
		// Delete this user
		Assert.assertFalse(response2.getBody().getRegistrationNumber().equals("41DCT"));
		personRepository.deleteById(response1.getBody().getId());
		personRepository.deleteById(response2.getBody().getId());
		Assert.assertEquals(200, response1.getStatusCode().value());
	}


	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

}
