package com.ch7;

public class ReturnTypeA {
	int[] methodD() {
		int deptno[] = {10,20,30};//deptno 지역변수
		return deptno;
	}
	public static void main(String[] args) {
		ReturnTypeA rta = new ReturnTypeA();
		int deptno[] = rta.methodD();
		//지변으로 선언된 배열도 다른 메소드영역에서 사용이 가능하다.
		//리턴타입의 역할
		for(int dno:deptno) {
			System.out.println(dno);
		}
	}

}
