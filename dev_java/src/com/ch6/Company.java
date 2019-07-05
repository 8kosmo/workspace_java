package com.ch6;

public class Company {
	private static Company instance = new Company(); 	//2
	private Company() {} 								//1
	public static Company getInstance() { 				//3
		if(instance == null) {
			instance = new Company();
		}
		return instance;
	}
}
