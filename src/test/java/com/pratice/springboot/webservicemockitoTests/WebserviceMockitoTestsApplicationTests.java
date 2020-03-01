package com.pratice.springboot.webservicemockitoTests;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebserviceMockitoTestsApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	/*
	 * By this "mocking" way we can ignore the actual implementation of the a class which loads with the whole context.
	 * and can implement our own behavior of the methods of this class with the BDD style(when->then).
	 * 
	 * @MockBean private ItemRepository itemRepository;
	 * */
	
	/* 
	 * Step-17: we can separate application.properties and data.sql file for our test environment
	 * 
	 * Method-1: src/test/resources/application.properties and data.sql
	 * Method-2: @TestPropertySources(location = {"classpath:filename.properties"})
	 * */
	
	@Test
	void contextLoads() throws JSONException {
		String repoString = testRestTemplate.getForObject("/all-item-from-database", String.class);
		
		JSONAssert.assertEquals("[{id:1},{id:2},{id:3},{id:4}]", repoString, false);
	}

}
