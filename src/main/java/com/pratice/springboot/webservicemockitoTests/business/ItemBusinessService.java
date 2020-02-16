package com.pratice.springboot.webservicemockitoTests.business;

import org.springframework.stereotype.Component;

import com.pratice.springboot.webservicemockitoTests.modal.Item;

@Component
public class ItemBusinessService {

	public Item retrieveHardcodedItem() {
		return new Item(1, "aloo", 50, 3);
	}

}
