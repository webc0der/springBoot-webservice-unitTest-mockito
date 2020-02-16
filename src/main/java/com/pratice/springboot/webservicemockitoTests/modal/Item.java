package com.pratice.springboot.webservicemockitoTests.modal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data()
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Item {

	private int id; 
	private String item; 
	private int price; 
	private int quantity;
}
