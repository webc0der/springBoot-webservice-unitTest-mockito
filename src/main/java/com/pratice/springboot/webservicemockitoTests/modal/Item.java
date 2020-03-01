package com.pratice.springboot.webservicemockitoTests.modal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data()
public class Item {

	@Id
	private int id; 
	private String item; 
	private int price; 
	private int quantity;
	
	@Transient
	private int value;
	
	
	
	protected Item() {
		
	}
	
	public Item(int i, String string, int j, int k) {
		this.id = i;
		this.item = string;
		this.price = j;
		this.quantity = k;
	}
}
