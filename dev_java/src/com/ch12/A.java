package com.ch12;

import java.util.ArrayList;

public class A {
	ArrayList<String> chatList = null;
//	ArrayList<String> chatList = new ArrayList<>();
	public A() {
		//디폴트생성자는 생략가능
		//그러나 파라미터가 있는 생성자는 생략불가
		chatList = new ArrayList<>();
		B b = new B(this);
//		chatList = new ArrayList<>();
	}
}
