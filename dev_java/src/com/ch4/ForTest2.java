package com.ch4;

public class ForTest2 {
//for문안에 변수를 선언하면 for문 안에서만 사용할 수 있다.
//초기화, 조건식, 증감연산자가 없으면 무한루프에 빠질 수 있다.- 서버가 중지
//반복문 사용시 주의사항 - 무한루프에 대한 방지 코드가 필요하다.
	public static void main(String[] args) {
		int i, j;
		for(i=1;i<4;i++) {
			if(i==2) {
				break;	
			}
			for(j=1;j<4;j++) {
				System.out.println(i+", "+j);
			}
		}
	}

	
}
