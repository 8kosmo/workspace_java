package com.ch2;

public class MoonWeight {

	public static void main(String[] args) {
		//지구의 몸무게를 담을 변수 선언해 볼까?
		int earth_weight = 100;
		String unit = "kg";//굳이 단위를 넣고 싶다면 추가한다.
		double moon_weight = 0.0;
		float a = 17;
		int b = 100;
		//int == 정수 float == 실수
		//정수/정수=정수, 정수/실수or실수/실수or실수/정수는 실수이다.
		System.out.println("a/b ===>"+(a/100));
		System.out.println("a/b ===>"+(a/100.0));
		System.out.println("a/b ===>"+(a/100.0f));
		//달의 몸무게를 계산해서 출력하시오.
		System.out.println(100*0.17+ unit);
		System.out.println(earth_weight*a/b+unit);
		System.out.println(earth_weight*(a/b)+unit);
					                  //(a/b)를 하면 왜 0으로 출력될까?
		int x = 5;
		int y = 0;
		y = x;
		moon_weight = earth_weight*a/b;
		System.out.println(moon_weight+ unit);
	}

}
