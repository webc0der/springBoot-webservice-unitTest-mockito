package com.pratice.springboot.webservicemockitoTests.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		 */
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

}
