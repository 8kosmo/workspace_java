package com.network1_a;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class ServerTest extends JFrame {
	ServerSocket sSocket = null;
	Socket socket = null;
	public void initDisplay() {
		this.setSize(500, 300);
		this.setVisible(true);
	}
	public void serverInit() {
		try {
			sSocket = new ServerSocket(5000);
			System.out.println("접속을 기다리는 중...");
			while(true) {
				socket = sSocket.accept();
				System.out.println("클라이언트가 접속해옴"+socket);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		ServerTest st = new ServerTest();
		//서버측에서 클라이언트가 접속해 올 때까지 대기상태에 빠지게됨.
		//따라서 화면이 열리지 않음.
		//두번째 문제는 여러 사용자가 동시에 또는 시간차를 두고
		//서버에 접속을 시도하게 됨에 따라 경합상태에 빠지게 됨.
		st.serverInit();
		st.initDisplay();
	}

}
