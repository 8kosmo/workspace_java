package com.network1_a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TimeServerVer2 extends JFrame implements Runnable{
	ServerSocket sSocket = null;
	Socket socket = null;
	List<ServerThread> threadList = null;
	JTextArea jta_log = new JTextArea();
	JScrollPane jsp_log = new JScrollPane(jta_log);
	ServerThread sThread = null;//외부에서 접속한 Thread를 관리하기 위한 전역변수
	
	public void initDisplay() {
		this.setTitle("서버측 로그 출력화면");
		this.add("Center",jsp_log);
		this.setSize(300, 200);
		this.setVisible(true);
	}////////////end of initDisplay
	public static void main(String[] args) {
		TimeServerVer2 ts2 = new TimeServerVer2();
		ts2.initDisplay();
		Thread th = new Thread(ts2);
		th.start();
	}
	@Override
	public void run() {
		//접속한 클라이언트의 정보를 담을 Vector추가(멀티스레드안전)
		threadList = new Vector<ServerThread>();
		try {
			sSocket = new ServerSocket(3000);
			while(true) {
				jta_log.append("접속을 기다립니다.\n");
				socket = sSocket.accept();
				System.out.println
				("접속한 클라이언트측 정보 출력하기==>"+socket.getInetAddress());
				jta_log.append(socket+"에 연결되었습니다."+"\n");
				sThread = new ServerThread(this);
				sThread.start();//run메소드 호출
			}////////////end of outter while
		} catch (IOException ie) {
			ie.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}////////////////////end of try...catch
	}////////////////////////end of run
}////////////////////////////end of class
