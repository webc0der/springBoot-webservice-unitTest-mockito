package com.pratice.springboot.webservicemockitoTests.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pratice.springboot.webservicemockitoTests.business.ItemBusinessService;
import com.pratice.springboot.webservicemockitoTests.modal.Item;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = HelloRestController.class)
public class HelloWorldControllerTest {

	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private ItemBusinessService businessService;

	static RequestBuilder helloWorld;
	static RequestBuilder dummyItem;
	static RequestBuilder dummyItemBusiness;
	static RequestBuilder itemFromDatabase;

	@BeforeAll
	public static void initializers() {
		helloWorld  = MockMvcRequestBuilders
				.get("/hello-world")
				.accept(MediaType.APPLICATION_JSON);

		dummyItem  = MockMvcRequestBuilders
				.get("/dummy-item")
				.accept(MediaType.APPLICATION_JSON);
		
		dummyItemBusiness  = MockMvcRequestBuilders
				.get("/dummy-item-busines-service")
				.accept(MediaType.APPLICATION_JSON);
		
		itemFromDatabase  = MockMvcRequestBuilders
				.get("/all-item-from-database")
				.accept(MediaType.APPLICATION_JSON);
		
		
		/*
		 * For different http request method testing
		 * *POST
		 * *PUT
		 * *DELETE
		 * 
		 * HttpHeaders urlHeader = new HttpHeaders();
		 * urlHeader.setContentType(MediaType.APPLICATION_JSON);
		 * 
		 * *WITHOUT HEADER
		 * MockMvcRequestBuilders .post("/post/vi/service-name")
		 * .accept(MediaType.APPLICATION_JSON) .content("JSON content")
		 * .contentType(MediaType.APPLICATION_JSON);
		 * 
		 * *WIT HEADER
		 * MockMvcRequestBuilders .post("/post/vi/service-name")
		 * .accept(MediaType.APPLICATION_JSON) .content("JSON content")
		 * .contentType(MediaType.APPLICATION_JSON).headers(urlHeader);
		 * */
		
		/* 
		 * For verifying Headers of response
		 * 
		 * mockMVC.perform(dummyItemBusiness)
		.andExpect(status().isOk())
		.andExpect(header().string(
				"location", containsString("/item/")
				))
		.andExpect(content().json(
				"{id:1,item:aloo,price:50,quantity:3}"
				)); 
		 * */ 

	}

	@Test
	public void helloWorld_basic() throws Exception {

		/* 
		 * Using MockMvc methods to match the result 
		 * */
		mockMVC.perform(helloWorld)
		.andExpect(status().isOk())
		.andExpect(content()
				.string("Hello World!"));

		/*
		 * Using assert to match the result
		 * 
		 * MvcResult result=
		 * mockMVC.perform(rb).andExpect(status().isOk()).andExpect(content().
		 * string("Hello World!")).andReturn();
		 * assertThat(result.getResponse().getContentAsString(),
		 * equalToIgnoringCase("Hello World!"));
		 * */
	}

	@Test
	public void dummyItem_acceptsNothing_returnsItemJSONObject() throws Exception {

		/* 
		 * Using MockMvc methods to match the result with content as json
		 * */
		mockMVC.perform(dummyItem)
		.andExpect(status().isOk())
		.andExpect(content()
				.json("{\"id\":1,\"item\":\"aloo\",\"price\":50,\"quantity\":3}"));
		
		/* 
		 * Using MockMvc methods to match the result with content as json and strict parameter
		 * true: can have white spaces between the data but not in the data and all the data should be there
		 * false: can have only select data to check 
		 * and with false no need to have escape charecters unless we have the space in the return data
		 * */
//		mockMVC.perform(dummyItem)
//		.andExpect(status().isOk())
//		.andExpect(content()
//				.json("{\"id\":1,\"item\":\"aloo\",\"price\":52,\"quantity\":3}", false));
	}
	
	@Test
	public void dummyItem_accessBusinessClass_returnsItemJSONObjectfromBusinessClass() throws Exception {
		
		/*
		 * when test rest service(controller methods) the we don't depend upon the its business logic to
		 * perform and return value, instead we derive the business logic in BDD style
		 * to return the expected value by mocking the business class.
		 */
		
		when(businessService.retrieveHardcodedItem()).thenReturn(new Item(1,"aloo",50,3));
		
		/* 
		 * Using MockMvc methods to match the result with content as json
		 * */
		mockMVC.perform(dummyItemBusiness)
		.andExpect(status().isOk())
		.andExpect(content()
				.json("{id:1,item:aloo,price:50,quantity:3}"));
		
	}
	
	@Test
	public void retrieveAllItemEndPoint_accessBusinessClass_returnsItemJSONObjectfromBusinessClass() throws Exception {
		
		/*
		 * when test rest service(controller methods) the we don't depend upon the its business logic to
		 * perform and return value, instead we derive the business logic in BDD style
		 * to return the expected value by mocking the business class.
		 */
		
		when(businessService.getAllItems())
		.thenReturn(
				Arrays.asList(new Item(2,"aloo",10,20),new Item(3,"piyaz",30,40))
				);
		
		/* 
		 * as per the when scenario we are returning an array of size:2
		 * so as below we are verifying the elements of the returning json array
		 * */
		mockMVC.perform(itemFromDatabase)
		.andExpect(status().isOk())
		.andExpect(content()
				.json("[{id:2,item:aloo,price:10,quantity:20},{id:3,item:piyaz,price:30,quantity:40}]"));
		
		
		/* 
		 * as per the when scenario we are returning an array of size:2
		 * so as below we can keep the empty array element
		 * to just match the size and not exactly the content 
		 * */
//		mockMVC.perform(itemFromDatabase)
//		.andExpect(status().isOk())
//		.andExpect(content()
//				.json("[{id:2,item:aloo,price:10,quantity:20},{}]"));
		
	}

}
