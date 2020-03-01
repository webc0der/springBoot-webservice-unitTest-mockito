package com.pratice.springboot.webservicemockitoTests.data_layer_repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pratice.springboot.webservicemockitoTests.Repository.ItemRepository;
import com.pratice.springboot.webservicemockitoTests.modal.Item;

/*
 * in the real database layer or Repository test 
 * the data.sql file must be in java/text/resources
 * */

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void test_FindAll() {
		List<Item> items = itemRepository.findAll();
		
		assertThat(items.size(), is(4));
	}
}
