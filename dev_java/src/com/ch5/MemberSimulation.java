package com.ch5;

public class MemberSimulation {

	public static void main(String[] args) {
		MemberVO memVO = new MemberVO();
		/*
		 * MemberVO에는 여러 변수가 선언되어 있음.
		 * 그러나 선언만 되어 있음. 초기화는 안되어 있음.
		 * 즉 어떤 정보도 가지고 있지 않는 상태임
		 * 어떤 정보를 초기화 해주어야 값을 꺼낼 수 있음.
		 */
		String mem_id = memVO.getMem_id();
		System.out.println(mem_id);//null출력
		//insert here - 멤버변수 mem_id 에 값을 담아보세요.
		memVO.setMem_id("test");
		System.out.println(memVO.getMem_id());//test출력
		//여기서 mem_id 은 13번에서 선언한 mem_id 이다.
		System.out.println(mem_id);//null출력
		mem_id = memVO.getMem_id();
		System.out.println(mem_id);//test출력
		//memVO.mem_id은 memVO.클래스의 전역변수 mem_id이고 private이기 때문에
		//사용할 수 없다.
		//System.out.println(memVO.mem_id);
	}

}
