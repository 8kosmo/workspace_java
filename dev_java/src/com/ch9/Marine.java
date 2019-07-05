package com.ch9;

public class Marine extends Unit {
	public Marine() {
		System.out.println("You wanna piece of me, boy?");
	}
	@Override
	void move(int x, int y) {
		// TODO Auto-generated method stub

	}
	void SteamPack() {
		
	}
	/*
	 * 오버라이딩은 반드시 부모메소드와 동일해야한다.(파라미터, 리턴타입)
	 * 단 접근 제한자는 더 넓은 것은 가능하다.public>protected=friendly>private
	 */
	@Override
	public void display() {
		System.out.println("Rock & Roll");
	}

}
