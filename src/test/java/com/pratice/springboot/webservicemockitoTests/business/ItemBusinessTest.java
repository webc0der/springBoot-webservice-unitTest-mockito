package com.pratice.springboot.webservicemockitoTests.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pratice.springboot.webservicemockitoTests.Repository.ItemRepository;
import com.pratice.springboot.webservicemockitoTests.modal.Item;

@ExtendWith(MockitoExtension.class)
class ItemBusinessTest {

	@InjectMocks
	private ItemBusinessService itemBusinessService;
	
	@Mock
	private ItemRepository itemRepository;
	
	@Test
	public void getAllItems_returnsAllItemFrom_returnsList() {
		when(itemRepository.findAll())
		.thenReturn(
				Arrays.asList(new Item(2,"aloo",10,20)
						,new Item(3,"piyaz",30,40)
						)
				);
		List<Item> allItems = itemBusinessService.getAllItems();
		
		/* 
		 * using hamcest matchers assertions
		 * 
		 * here every for every condition we need to write new assertion
		 * */
//		assertThat(allItems.get(0).getValue(), is(200));
//		assertThat(allItems.get(1).getValue(), is(1200));
		
		/* 
		 * using assertj assertions
		 * 
		 * here we are checking multiple condition in one line
		 * */
		assertThat(allItems.get(0).getValue())
		.isEqualTo(200)
		.isNotNull()
		.matches(x -> x >10);
		
		/* 
		 * using assertj assertions
		 * For String
		 * here we are checking multiple condition in one line
		 * */
		assertThat(allItems.get(0).getItem())
		.contains("aloo")
		.startsWith("a")
		.endsWith("o");
	}
}
