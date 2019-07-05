package com.ch5;

public class MemberSimulation3 {

	public static void main(String[] args) {
		MemberVO mVO = new MemberVO();
		mVO = new MemberVO(10);
		//파라미터에 준 상수값들은 XXXX에 담긴다.
		mVO = new MemberVO("test","123","이순신","서울","집주소") {
			
		}
	}

}
