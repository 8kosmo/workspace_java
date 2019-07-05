package com.ch5;

public class TVSimulation {
	
	public static void main(String[] args) {
		TV myTV = new TV();
		TV herTV = new TV();
		TV himTV = new TV();
		System.out.println(himTV.onoff);//TV클래스에서 onoff전역변수 출력
		himTV.onoff = true;//전역변수onoff에 true대입
		System.out.println(himTV.onoff);
		TV ourTV = null;
		ourTV = new TV(true);//전역변수 ourTV은 아직 초기화가 안되어있어서 false
		System.out.println(ourTV.onoff);
		ourTV.onoff = true;
		System.out.println(ourTV.onoff);
	}

}
