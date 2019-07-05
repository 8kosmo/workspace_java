package com.ch7;

import java.util.Random;

public class RamdomGame2 {
	int nansu[]= new int[10];
	void methodA() {
		Random r = new Random();
		for(int i=0;i<nansu.length;i++) {
			nansu[i] = r.nextInt(21);
		}
	}
	void methodB() {
		for(int i=0;i<nansu.length;i++) {
			System.out.print(nansu[i]+" ");
		}
	}
	public static void main(String[] args) {
		RamdomGame2 rg = new RamdomGame2();
		rg.methodA();
		rg.methodB();
	}

}
