package com.ch4;
import java.util.Scanner;
public class Q2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int step1 = scan.nextInt();
		int step2 = scan.nextInt();
		int cnt = 0;
		for(int i=step1+1 ; i<step2 ; i++) {
			if(i%3==0) {
				cnt ++;
			}
		}
		System.out.println("3의 배수는 "+cnt);
	}

}
