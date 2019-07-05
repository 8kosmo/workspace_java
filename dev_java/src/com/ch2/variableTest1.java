package com.ch2;
//학습목표 3가지 i를 구분하시오.
public class variableTest1 {
	//non-static타입의 변수는 static영역에서 사용불가
	//해결방법이 있다. -인스턴스화를 하면 가능함.
	//전역변수는 초기화를 생략할 수도 있다.이유? 생성자가 대신 해준다.
	int i=500;//전역변수이다. 다른 메소드에서도 사용이 가능하다.
	void methodA() {
		//지역변수는 반드시 초기화를 해야됨. 안하면 컴파일 에러
		int i;//지역변수이다.
		i=10;
		System.out.println(i);//100?10?
	}
	void methodA(int i) {//메소드의 파라미터 자리에도 지역변수를 선언할 수 있다.
		System.out.println(i);
	}
	void methodA(String id, String pw) {//메소드의 파라미터 자리에도 지역변수를 선언할 수 있다.
		System.out.println(i);}
	public static void main(String[] args) {
		variableTest1 vt = new variableTest1();//클래스이름과 같아야한다.
		
		vt.methodA();//메소드 호출하기 // i=10을 출력하기 위한
		System.out.println(vt);//@abcd1234주소번지
		vt.methodA("test","1234");
		System.out.println(vt.i);
		vt.methodA(30);
		//전역변수int i=100을 출력하기 위해 vari.....Test1(); 인스턴스화가 필요하다.
	}

}
