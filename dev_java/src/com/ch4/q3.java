package com.ch4;

public class q3 {
//피보나치 수열의 규칙을 알아보고 a1항에서 a10번째 항까지 출력하는 프로그램을 작성하시오.
	public static void main(String[] args) {
		int a=1, b=1, c=0;
		System.out.println(a+" "+b+" ");
		for(int i=0;i<=18;i++) {
			c = a + b;
			System.out.println(c+" ");
			a = b;
			b = c;
		}
	}

}
//0 1 1 2 3 5 8 13 21 34 