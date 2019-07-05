package com.network2_a;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MessageRoom extends JPanel {
	//선언부
	TalkClientVer2 tc2 = null;
	
	//생성자
	public MessageRoom() {
		
	}//end of MessageRoom
	public MessageRoom(TalkClientVer2 tc2) {
		this.tc2 = tc2;
	}
	//화면처리부
	public void initDisplay() {
		
	}//end of initDisplay
	//단위테스트 때문에 추가함 - TalkClientVer2에 붙일것임
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
