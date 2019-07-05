package com.ch7;

import java.util.Random;

public class RamdomGame1 {
	int a[] = new int[10];
	public void order() {
		int tmp = 0;
		for(int i=0;i<a.length;i++) {
			for(int j=i+1;j<a.length;j++) {
				if(a[i] > a[j]) {// i=0,j=1
					tmp = a[i];
					a[i]= a[j];
					a[j]= tmp;
				}/////////end of if
			}/////////////end of inner for
		}/////////////////end of outer for 
	}
	public void arrayInit() {
		Random r = new Random();
		for(int i=0;i<a.length;i++) {
			a[i] = r.nextInt(21);
		}
	}
	public void arrayPrint() {
		for(int i=0;i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
	}
	public static void main(String[] args) {
		RamdomGame1 r1 = new RamdomGame1();
		r1.arrayInit();
		r1.order();
		r1.arrayPrint();
	}

}
