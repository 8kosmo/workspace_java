package com.ch4;

public class FizzBuzz {
//FizzBuzz게임 구현해보자.1부터 100까지 세면서 5의배수이면 fizz라고 출력하고 7의 배수이면 buzz라고 출력
//5와 7의 공배수이면 fizzbuzz
	public static void main(String[] args) {
			int v ;
			for(v=1;v<=100;v++) {
			if(v%35==0) {
				System.out.println("FizzBuzz");
			}
			else if(v%5==0) {
				System.out.println("Fizz");
			}
			else if(v%7==0) {
				System.out.println("Buzz");
			}
			else {
				System.out.println(v);
			}
		}
	}
}