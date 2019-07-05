package com.ch2;

public class practice {
	String j = "전역변수";
	int s = 100;
	void methodA() {
		String j = "지역변수A";
		System.out.println(j);
	}
	void methodA(String j) {
		j = "지역변수B";
		System.out.println(j);
	}
	void methodB( ) {
		System.out.println("지역변수C");
	}
	int speed(int sp) {
		return sp;
	}
	void speed2(int sp2) {
		System.out.println("Speed는 "+sp2);
	}
	public static void main(String[] args) {
		practice pt = new practice();
		System.out.println(pt.j);
		pt.methodA();
		pt.methodA("");
		pt.methodB();
		System.out.println("Speed는 "+pt.s);//Speed는 100
		System.out.println("Speed는 "+pt.speed(90));//Speed는 90
		pt.speed2(80);
		
	}

}
