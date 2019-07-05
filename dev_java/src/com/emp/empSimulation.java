package com.emp;

public class empSimulation {

	public static void main(String[] args) {
		empCtrl empctrl = new empCtrl();
		empVO paVO = new empVO();
		empVO raVO = null;
		paVO.setCommand("insert");
		paVO.setId("javachip");
		paVO.setName("james Gosling");
		paVO.setHp("01088008800");
		try {
			raVO = empctrl.send(paVO);
			if(raVO.getStatus()==1) {
				System.out.println("Insert성공");
			}else {
				System.out.println("왜안되,씨발");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		paVO = new empVO();
		raVO = null;
		paVO.setCommand("update");
		paVO.setId("scott");
		paVO.setName("tiger");
		paVO.setHp("01077007700");
		try {
			raVO = empctrl.send(paVO);
			if(raVO.getStatus()==1) {
				System.out.println("Update성공");
			}else {
				System.out.println("왜안되,씨발");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
