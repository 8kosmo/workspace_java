package com.ch5;
/*
 * 2007년 다음에서 발표한 자료에 따르면 1초당 2720명이 접속을 시도한다고 함.
 * 이중에서 500명만 로그인을 한다고 가정하더라도 이 500명의 정보를 따로 관리해야 함.
 * 그러기 위해서는 같은 테이블에서 읽어온 정보이지만 자바단에서 식별이 가능해야 하므로
 * 타입은 같지만 객체 생성을 500번 했을 경우 다른 객체로 인식되어 각각 다른 아이디를
 * 기억하고 있음.
 * 변수의 장애 - 같은 타입 1개만
 * VO패턴설계 - 서로 다른 타입 n개를 담을 수 있다.
 * 인스턴스화를 통해서 전역변수 초기화 가능.
 */
public class MemberSimulation2 {

	public static void main(String[] args) {
		MemberVO memVO1 = new MemberVO();
		memVO1.setMem_id("haha");
		MemberVO memVO2 = new MemberVO();
		memVO2.setMem_id("apple");
		MemberVO memVO3 = new MemberVO();
		memVO3.setMem_id("nono");
		//insert here - 
		System.out.println(memVO1.getMem_id());
		System.out.println(memVO2.getMem_id());
		System.out.println(memVO3.getMem_id());
		
		MemberVO memVO = new MemberVO();
		memVO.setMem_id("haha1");
		System.out.println(memVO.getMem_id());
		memVO = new MemberVO();
		memVO.setMem_id("apple1");
		System.out.println(memVO.getMem_id());


	}

}
