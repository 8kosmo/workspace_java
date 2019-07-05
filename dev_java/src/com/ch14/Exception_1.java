package com.ch14;

public class Exception_1 {
	void methodA() {
		System.out.println("methodA호출");
	}
	/*
	 * try..catch블록은 예외상활이 발생했을 경우에만 효과가 있다.
	 * 예외상황이 없다면 있으나 마나한 코드가 된다.
	 */
	public static void main(String[] args) {
		Exception_1 exc1 = null;
		try {
			exc1.methodA();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println("next to do");
	}

}
