package com.ch2;

public class VariableTest2 {
	void methodC(double pi1) {
		System.out.println("pi:"+pi1);
	}
	public static void main(String[] args) {
		VariableTest2 vt = new VariableTest2();
		vt.methodC(3.14);
	}

}
