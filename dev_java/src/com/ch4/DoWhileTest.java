package com.ch4;

import java.util.Random;

public class DoWhileTest {

	public static void main(String[] args) {
		int first = -1;
		int second = -1;
		int third = -1;
		Random r = new Random();
		for(int i=0;i<10;i++) {
			first = r.nextInt(10);
		    do {
			second = r.nextInt(10);
		    }while(first==second);
		    do {
			third = r.nextInt(10);
		    }while((first==third)||(second==third));
		    System.out.println(first+""+second+""+third);
		}
	}
}