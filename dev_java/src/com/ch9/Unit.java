package com.ch9;
//public > protected > friendly > private
public abstract class Unit {
	int x, y;
	public Unit() {
		System.out.println("Unit Default 생성자 호출 성공");
	}
	abstract void display();
//	abstract void move(int x,int y);
	void stop() {}
}
