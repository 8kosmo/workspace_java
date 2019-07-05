package com.ch5;

public class DVDVOsimulation {

	public static void main(String[] args) {
		DVDVO dVO = new DVDVO();
		//방3개, 그안에 있는건 null
		DVDVO dVOS[] = new DVDVO[3];
		for(DVDVO dVO2:dVOS) {
			System.out.println(dVO2);
		}
		for(int i=0;i<dVOS.length;i++) {
			System.out.println(dVOS[i]);
		}
		//String D_name = dVOS[0].getD_name();
		//System.out.println(D_name);
		dVO.setD_name("악인전");
		dVO.setD_actor("마동석 외 2명");
		dVO.setD_open("5월15일");
		dVOS[0] = new DVDVO("악인","마동석 외 2명","5월15일");
		String a = dVO.getD_name();
		dVOS[0].setD_name(a);
		dVO.setD_name("어벤져스:엔드게임");
		dVO.setD_actor("스칼렛요한슨 외 8명");
		dVO.setD_open("4월24일");
		dVOS[1]=dVO;
		for(int i=0;i<2;i++) {
			if(dVOS[i]!=null) {
			System.out.println(dVOS[i].getD_name()+","+dVOS[i].getD_actor()+","+dVOS[i].getD_open());		
	 		}else {
	 		System.out.println("dVOS[0]안에 객체가 생성되지 않았음");
	 		}
		}
		
    }

}
