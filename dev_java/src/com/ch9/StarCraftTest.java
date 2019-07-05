package com.ch9;

import com.ch10.Movable;

public class StarCraftTest {

	public static void main(String[] args) {
		Unit myMar = new Marine();
		Unit myTan = new Tank();
		Unit myDro = new DropShip();
//		Unit unit = new Unit(); //추상클래스는 반드시 구현체 클래스가 있어야한다.
		//그렇다면 생성자는 어떻게 호출되는 걸까?
		//결론:호출된 생성자의 상위생성자 먼저 호출된다.
		//오버라이드 규칙을 체크해야 되니까.
		//오버라이드인지 판별하려면 부모클래스를 먼저 스캔해야하지 않을까?
		//아래 3개의 유닛이 모두 같은 메소드를 호출하였다. 결과다 각각 다르다.
		myMar.display();
		myTan.display();
		myDro.display();
		/*
		 * 추상클래스와 인터페이스는 반드시 구현체 클래스를 갖는다.
		 */
		Movable mov = new DropShip();
		mov.move(200, 50);
	}

}
