package com.network1_a;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * API에서 필요한 클래스를 찾았다면 가장 먼저 생성자를 확인합니다.
 * 파라미터를 보고 선택
 * 자바에서 스레드 구현방법
 * 1)Thread를 상속받는다.
 * TimeServer ts = new TimeServer();
 * ts.start(); //호출 run();
 * 2)Runnable을 implements한다.
 * TimeServer ts = new TimeServer();
 * Thread th = new Thread(ts);
 * th.start();
 */
public class TimeServer extends JFrame implements Runnable{
	ServerSocket sSocket = null;
	Socket socket = null;//실제로 대화하는데 필요한 소켓
	
	JTextArea jta_log = new JTextArea();
	JScrollPane jsp_log = new JScrollPane(jta_log);
	
	public TimeServer() {
	}
	public void initDisplay() {
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent e) {				
			}
			@Override
			public void windowClosed(WindowEvent e) {				
			}
			@Override
			public void windowClosing(WindowEvent e) {
				//자바가상머신과 연결고리 끊기 - Candidate 상태로 만듬 - 자원반납
				System.exit(0);
			}
			@Override
			public void windowDeactivated(WindowEvent e) {				
			}
			@Override
			public void windowDeiconified(WindowEvent e) {				
			}
			@Override
			public void windowIconified(WindowEvent e) {			
			}
			@Override
			public void windowOpened(WindowEvent e) {				
			}	
		});
		this.setTitle("서버측 로그 출력화면");
		this.add("Center",jsp_log);
		this.setSize(300, 200);
		this.setVisible(true);
	}
	//현재 시간 정보 만들기
	public String currentTime() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		return (hour < 10 ? "0"+hour:""+hour)
				+":"+
			   (min < 10 ? "0"+min:""+min)
			    +":"+
			   (sec < 10 ? "0"+sec:""+sec);
	}
	public static void main(String[] args) {
		//화면처리를 생성자에서 하고 있으므로 서버소켓 생성에 앞서
		//먼저 생성자를 호출한다.
		TimeServer ts = new TimeServer();
		ts.initDisplay();
		Thread thread = new Thread(ts);
		thread.start();
		//포트번호는 1~65574
		//보통 운영체제가 1~1023
		//메소드 안에서 for문 혹은  try..catch블럭은{}
		System.out.println("서버 가동 성공");
	}
	@Override
	public void run() {
		try {
			sSocket = new ServerSocket(3000);
			while(true) {
				jta_log.append("접속을 기다립니다.\n");
				socket = sSocket.accept();
				System.out.println
				("접속한 클라이언트측 정보 출력하기==>"+socket.getInetAddress());
				jta_log.append(socket+"에 연결되었습니다."+"\n");
				PrintWriter out = 
						new PrintWriter(socket.getOutputStream(),true);
				while(true) {
					out.println(currentTime());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
						System.out.println("인터셉트를 당한 경우...");
					}
				}
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
