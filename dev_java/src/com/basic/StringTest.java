package com.basic;

public class StringTest {

	public static void main(String[] args) {
		String s = new String("hello");
		s.replace('e', 'o');
		s=s+" world";
		s=s+"!!!!!";
		System.out.println(s);
	}

}
