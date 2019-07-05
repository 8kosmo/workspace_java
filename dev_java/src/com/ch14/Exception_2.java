package com.ch14;

public class Exception_2 {
	/*
	 * 멀티블럭으로 처리할 경우 하위 클래스에서 상위클래스 순으로 작성함
	 * 만일 같은 이름의 exception이 존재하지 않으면 더 넓은범위(상위클래스)가 잡는다.
	 */
	void methodA() throws Exception {
		System.out.println("methodA호출");
		int x=5/0;
	}
	/*
	 * try..catch블록은 예외상활이 발생했을 경우에만 효과가 있다.
	 * 예외상황이 없다면 있으나 마나한 코드가 된다.
	 */
	public static void main(String[] args) {
		Exception_2 exc1 = new Exception_2();
		try {
			exc1.methodA();
		} catch(NumberFormatException ne) {
			System.out.println(ne.toString());
		} catch (ArithmeticException ae) {
			System.out.println(ae.toString());
		} catch (Exception e) {
			
		}
		System.out.println("next to do");
	}

}
