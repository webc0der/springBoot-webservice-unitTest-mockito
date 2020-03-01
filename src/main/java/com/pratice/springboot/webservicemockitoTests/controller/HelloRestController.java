package com.pratice.springboot.webservicemockitoTests.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratice.springboot.webservicemockitoTests.business.ItemBusinessService;
import com.pratice.springboot.webservicemockitoTests.modal.Item;

@RestController
public class HelloRestController {

	@Autowired
	private ItemBusinessService businessService;
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@GetMapping("/dummy-item")
	public Item dummyItem() {
		return new Item(1,"aloo",50,3);
	}
	
	@GetMapping("/dummy-item-busines-service")
	public Item dummyItemFromBusinessService() {
		return businessService.retrieveHardcodedItem();
	}
	
	@GetMapping("/all-item-from-database")
	public List<Item> dummyItemFromDatabase() {
		return businessService.getAllItems();
	}
}
