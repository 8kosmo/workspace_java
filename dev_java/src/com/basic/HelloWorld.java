package com.basic;
//나는 클래스 입니다.
//클래스 이름은 HelloWorld 입니다.{}
public class HelloWorld {

	public static void main(String[] args) {
		// 여기는 메인메소드 입니다.() 배열[]
		//명사형을 쓸 수 있다.
		//변수 선언하기
		//대입연산자 =
		//같다 1==1 참(true)
		//자바에서는 여러가지 타입을 제공함.
		//오라클에 저장된 정보가 여러가지임
		//SELECT ename, sal FROM emp
		int sal = 800;
		sal = 700;
		//정수를 담을 때는 타입을 반드시 int를 사용한다.
		System.out.println(sal);
		System.out.println(sal+10);
		System.out.println(sal-10);
		sal = sal+10;
		System.out.println(sal+100);
		System.out.println(sal-100);
		System.out.println(1000);//1000은 상수이다.
		//system - 너의 PC에
        //println() - 출력 메소드
		//name="SMITH"
	}
//Ctrl+S 저장 된 후 즉시 컴파일 해줌(이클립스)
}
