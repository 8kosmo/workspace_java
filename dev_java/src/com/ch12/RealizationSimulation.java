package com.ch12;

public class RealizationSimulation {

	public static void main(String[] args) {
		InterfaceParent ip = new Realization();
		Realization rt = new Realization();
		/*
		 * 완결편
		 * 자식은 부모가 가진 것들을 누릴 수 있다.
		 * 부모타입은 자신에게 있고 자식에게 있는 거는 가능
		 * 자식에는 있지만 자신에 없는 것은 불가
		 */
//		ip.methodA();
		rt.methodA();
	}

}
