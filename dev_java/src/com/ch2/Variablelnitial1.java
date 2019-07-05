package com.ch2;

public class Variablelnitial1 {
	int a1, a2, a3=0;
	void initial() {
		a1 = 7;
		a2 = 14;
		a3 = 21; //전역변수 int를 initial메소드에서 사용할 수 있다?
	}
	void methodA() {
		int account;
		//insert here
		account = a1+a2+a3;
		System.out.println("a1+a2+a3="+account+" 입니다.");
	}
	public static void main(String[] args) {
		Variablelnitial1 vl1 = new Variablelnitial1();
		vl1.initial();
		vl1.methodA();
	}

}
