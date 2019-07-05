package com.ch4;
/*
 *-10에서 10사이의 정수 10개를 랜덤하게 채번하여 음수와 양수의 합계를 구하는 프로그램을 작성하시오
 *9 -2 -3 8 0 -3 -8 -6 -2 0
 *음수의 합:-24
 *양수의 합:17
*/
import java.util.Random;
public class FinalQuestion {
	public static void main(String[] args) {
		Random ran = new Random();
		int r, x=0, y=0;
		for(int i=1;i<=10;i++) {
			r=ran.nextInt(21)-10;
			System.out.print(r+" ");
			if(r>0) {
				x += r;
			}
			else if(r<0) {
				y += r;
			}
		}
		System.out.println();
		System.out.println("양수의 합은 "+x);
		System.out.println("음수의 합은 "+y);
	}
}