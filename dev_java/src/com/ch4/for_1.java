package com.ch4;

public class for_1 {

	public static void main(String[] args) {
		int i;
		int sum;
		for (i=1,sum=0;i<=10;i++) {
			if(i%2==0) {
				sum+=i;
			}
		}
		System.out.println("짝수의 합은 "+sum);
		for (i=1,sum=0;i<=10;i++) {
			if(i%2!=0) {
				sum+=i;
			}
		}
		System.out.println("홀수의 합은 "+sum);

	}

}
