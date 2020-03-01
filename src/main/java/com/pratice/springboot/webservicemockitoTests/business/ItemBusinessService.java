package com.pratice.springboot.webservicemockitoTests.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pratice.springboot.webservicemockitoTests.Repository.ItemRepository;
import com.pratice.springboot.webservicemockitoTests.modal.Item;

@Component
public class ItemBusinessService {

	@Autowired
	private ItemRepository itemRepository;

	public Item retrieveHardcodedItem() {
		return new Item(1, "aloo", 50, 3);
	}

	public List<Item> getAllItems() {
		List<Item> itemList = itemRepository.findAll();
		
		for(Item item : itemList) {
			item.setValue(item.getPrice() * item.getQuantity());
		}
		return itemList;
	}

}
