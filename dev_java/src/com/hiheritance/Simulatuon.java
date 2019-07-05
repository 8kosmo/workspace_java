package com.hiheritance;

public class Simulatuon {
/*
 * 자바의 특징 - 어떻게 하면 자바답게 코딩을 할까? - 재사용성과 다형성
 * 인스턴스화 할 때 선언부와 생성부의 이름이 다를 수 있다.
 * Supa sup2 = new Sub(); 생성부에 오는 클래스이름으로 객체생성된다.
 * Supa 타입의 변수이고 실제로 생성된 객체는 Sub이다.
 * 선언부의 타입과 생성부의 타입이 다를 수 있다. 이때 다형성을 생각할 수 있다.선언부와 생성부타입이 같으면 다형성X
 */
	public static void main(String[] args) {
		Father fa = new Father();
		System.out.println("=====================");
		Son so = new Son();
		System.out.println("=====================");
		//기존에 가리키던 객체를 재생성하기 위해서 null로 초기화 한후
		so = null;
		//다시 생성하였음. 이때 파라미터를 갖은 생성자를 호출함.
		//그러면 생성자 내부에서 전역변수 book이 다시 초기화됨.
		so = new Son("경국대전");
		//출력해보면 성경책에서 경국대전으로 바뀐것을 확인 할 수 있음.
		System.out.println(so.book);
		fa.walk();
		so.walk();
		so.walk("동생이");
		/*********************************************************/
		FigureArea FA = new FigureArea();
		double t_area = FA.area(10, 15, 0.5);
		System.out.println(t_area);//75.0
		double r_area = FA.area(20, 15);
		System.out.println(r_area);//300.3
		double c_area = FA.area(0.5, 3.14);
		System.out.println(c_area);//0.785
		/*********************************************************/
		Sub sup1 = new Sub();
		Supa sup2 = new Sub();//선언부Supa과 생성부Sub 클래스가 달라졌을 때.
		//methodA와 methodB를 호출하시오.
		sup2.methodA();
		sup2.methodB();//sub클래스에서 상속관계를 갖고 있는 상위supa메소드를 호출 할 수 있다.
	}
 
}
