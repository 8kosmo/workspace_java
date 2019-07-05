package com.ch10;
/*
 * 추상클래스보다 더 추상적이다.
 * 일반메소드를 가질 수 없다.
 * 생성자 가질 수 없다.
 * 일반변수 가질 수 없다.
 */
public interface Movable {
	final int i=0;
	public abstract void back();
	abstract void move(int x,int y);
}
