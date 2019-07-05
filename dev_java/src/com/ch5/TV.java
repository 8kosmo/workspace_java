package com.ch5;

import javax.swing.JButton;

public class TV {
	JButton jbtn_onoff = new JButton("전원");
	JButton jbtn_plus = new JButton("+");
	boolean onoff = false;
	int volumn = 0;
	//전역변수 - 초기화를 생략가능함.
	//왜냐하면 생성자가 대신 해주니까.
	public TV() {//생성자라고 함.
		System.out.println("디폴트 생성자 호출 성공");
	}
	public TV(boolean onoff) {//생성자라고 함.
		System.out.println("파라미터가 있는 생성자 호출 성공");
	}
	public void powerOn() {
		onoff = true;
	}
	public void powerOff() {
		onoff = false;
	}
}
