package com.pratice.springboot.webservicemockitoTests.business;

public class SomeBusinessImpl {

	public int calculateSun(int[] data) {
		int sum = 0;
		for(int val : data)
			sum += val;
		return sum;
	}
}
