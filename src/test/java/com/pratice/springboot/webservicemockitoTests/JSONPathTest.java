package com.pratice.springboot.webservicemockitoTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JSONPathTest {

	@Test
	public void testJSONContent_With_JSONPath() {
		String jsonResponse = "[{\"id\":1,\"item\":\"aloo\",\"price\":10,\"quantity\":20,\"value\":200},{\"id\":2,\"item\":\"gobi\",\"price\":20,\"quantity\":60,\"value\":1200},{\"id\":3,\"item\":\"piyaz\",\"price\":30,\"quantity\":20,\"value\":600},{\"id\":4,\"item\":\"adrak\",\"price\":40,\"quantity\":70,\"value\":2800}]";
		
		DocumentContext context = JsonPath.parse(jsonResponse);
		int length = context.read("$.length()");
		assertThat(length).isEqualTo(4);
		
		
	}
}
