package com.network1_a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimeClient extends JFrame implements Runnable{
	//선언부
	JLabel jlb_time = new JLabel("현재시간",JLabel.CENTER);
	Socket client = null;
	//생성자
	public TimeClient() {
		
	}
	public void initDisplay() {
		this.setSize(500, 400);
		this.setVisible(true);
		this.setTitle("내 단말기");
		this.add("North",jlb_time);
//		jlb_time.setFont(jlb_time.getFont().deriveFont(40));
	}
	//소켓 초기화
	public void init(String ip, int port) {
		try {
			client = new Socket(ip,port);
		} catch (UnknownHostException ue) {
	
		} catch(IOException io) {
			
		}
	}
	//메인메소드
	public static void main(String[] args) {
		TimeClient tc = new TimeClient();
		//화면을 처리하는 메소드 호출하기
		tc.initDisplay();
		System.out.println("Socket 정보확인(before):"+tc.client);
		tc.init("192.168.0.22",3000);
		Thread thread = new Thread(tc);
		thread.start();//run()호출하기
		System.out.println("Socket 정보확인(after):"+tc.client);
	}
	@Override
	public void run() {
			PrintWriter out = null;
			BufferedReader in = null;
			String timeInfo = null;
			try {
				out = new PrintWriter(client.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				while(true) {
					while((timeInfo=in.readLine())!=null) {
						jlb_time.setText(timeInfo);
						Thread.sleep(1000);
					}
				}
			}catch (InterruptedException ie) {
					ie.printStackTrace();
			}catch (Exception e) {
					e.printStackTrace();
			}finally {
				 try {
						in.close();
						out.close();
						client.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			
		}
}