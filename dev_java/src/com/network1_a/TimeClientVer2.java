package com.network1_a;

import java.net.Socket;

import javax.swing.JFrame;
/*
 * TimeClientVer2는 스레드가 필요하지 않아요
 * 왜냐하면 클라이언트는 서버를 개설할 필요가 없다.
 */
import javax.swing.JLabel;
public class TimeClientVer2 extends JFrame{
	Socket client = null; // 내가 필요한 소켓정보 하나만...
	//AddressBook에 생성한 jlb_time에 시간정보를 출력하니까
	//인스턴스화 하지않고 AddressBook에서 원본 주소번지를 받아옴
	JLabel jlb_time = new JLabel("현재시간",JLabel.CENTER);
	
	public TimeClientVer2() {}
	
	public TimeClientVer2(JLabel jlb_time) {
		this.jlb_time = jlb_time;
	}
	
	public void initDisplay() {
		this.add("North",jlb_time);
		this.setTitle("내 단말기");
		this.setSize(500, 400);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		TimeClientVer2 tc2 = new TimeClientVer2();
		tc2.initDisplay();
		try {
			ClientThread ct = new ClientThread(tc2);
			ct.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
