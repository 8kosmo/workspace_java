package com.ch2;

public class Qustion5 {

	public static void main(String[] args) {
		//1부터 5까지 합을 구하는 프로그램을 작성하시오.
		//sum = 1+2+3+4+5;
		int sum = 0;
		int i = 1;
		for(i=1;i<=5;i=i+1) {
			sum = sum + i;
			System.out.println("sum==>"+sum);
		}
		System.out.println("1부터 5까지의 합은 "+sum);//15
		//1부터 10까지 홀수의 합을 구하시오.
		//sum = 1+3+5+7+9;
		sum = 0; //초기화를 하지 않으면 전에 나온 sum 출력값이 다음 출력값에 가산된다.
		for(i=1;i<=5;i=i+2) {
			sum = sum + i;
			System.out.println("sum===>"+sum);
		}
		System.out.println("1부터 5까지의 홀수의 합은 "+sum);//9
	}

}
