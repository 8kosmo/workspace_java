package com.ch14;

public class Exception_3 {
	void methodA(String imsi) {
		System.out.println("methodA호출");
		int x = Integer.parseInt(imsi);
		throw new NumberFormatException();
	}
	/*
	 * try..catch블록은 예외상활이 발생했을 경우에만 효과가 있다.
	 * 예외상황이 없다면 있으나 마나한 코드가 된다.
	 */
	public static void main(String[] args) {
		Exception_3 exc1 = null;
		try {
			exc1 = new Exception_3();
			exc1.methodA("123");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println("next to do");
	}

}
