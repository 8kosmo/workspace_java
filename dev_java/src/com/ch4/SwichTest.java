package com.ch4;

public class SwichTest {

	public static void main(String[] args) {
		int i = 4;
		switch(i){
		case 1: System.out.println(i);
		//break;
		case 2: System.out.println(i);
		break;
		case 3: System.out.println(i);
		break;
		default: System.out.println(i-4);
		break;
		}///////////end of switch
		System.out.println("여기");
		int j=++i;
		System.out.println("j는 "+j);
	}///////////////end of main
}///////////////////end of SwichTest
