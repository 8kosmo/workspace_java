package com.ch4;

public class Geometric {
/*
 * 여기서는 첫째 항이2, 공비가 3인 등비수열을 적용해 본다.
 */
	public static void main(String[] args) {
		//등비수열의 항에 대응하는 값을 저장할 변수선언
		int n=2;
		for(int i=1;i<=7;i++) {
/*
 * printf함수를 사용하면 출력되는 값에 자리수를 정해 줄 수 있음
 * %5에서 숫자 5가 자리수를 나타냄.
 * d는 10진수를 말함
 * 만일 문자열이면 %s, character는 %c 
 */
			System.out.printf("%5d",n);
			n*=3;
		}
	}

}
