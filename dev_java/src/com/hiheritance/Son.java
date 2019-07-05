package com.hiheritance;

public class Son extends Father{
	String book = "성경책";
	//생성자는 method overloading의 규칙을 준수한다.
	public Son() {
		System.out.println("Nothing");
	}
	public Son(String book) {
		this.book = book;
	}
	public void walk(String j) {
		System.out.println(j+"랑 걷는 중");
	}
	@Override
	public void walk() {
		System.out.println("엄마랑 걷는 중");
	}
}
