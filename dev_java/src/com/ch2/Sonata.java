package com.ch2;

import com.ch9.Car;

public class Sonata extends Car{
	int wheelNum = 4;
	int speed = 0;
	String carColor = "빨강";
	void move(int i) {//지역변수
		speed = speed+1;
		i=speed;
		System.out.println("지역변수 i는 "+i);
	}
	public static void main(String[] args) {
		//insert here - move메소드 호출하기
		Sonata hiscar = new Sonata();//클래스이름 인스턴스이름 = new 클래스이름();
		//너 바퀴수가 몇개니?
		System.out.println(hiscar.wheelNum);
		System.out.println("지우의 차는 "+hiscar.carColor);
		hiscar.move(100);
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Unit() {
		// TODO Auto-generated method stub
		
	}

}
