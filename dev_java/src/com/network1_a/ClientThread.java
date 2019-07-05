package com.network1_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	TimeClientVer2 tc2 = null;
	public ClientThread(TimeClientVer2 tc2) {
		this.tc2 = tc2;
	}
	@Override
	public void run() {
			PrintWriter out = null;
			BufferedReader in = null;
			//서버측에서 들은 내용을 담을 변수 선언
			String timeInfo = null;
			try {
				tc2.client = new Socket("192.168.0.14",3000);
				//사용자가 설치한 (혹은 가진 단말기) 어플에서 말해야하니까
				//소켓객체를 활용하여 객체생성을 해야함.
				out = new PrintWriter(tc2.client.getOutputStream(),true);
				//위와 동일한 이유로 듣기 필요한 객체를 생성하였음.
				in = new BufferedReader(new InputStreamReader(tc2.client.getInputStream()));
				//한번만 듣는 것이 아니라 1초단격으로 계속들어야함
				//따라서 반복문 처리하였음.
				while(true) {
					//서버에서 읽어들인 정보가 null이 아닌 동안에는 계속 담음
					while((timeInfo=in.readLine())!=null) {
						//timeInfo에는 17:40:35 시간 정보가 담겨 있으므로
						//그 정보를 북쪽JLabel에 출력하는 메소드 호출함.
						tc2.jlb_time.setText(timeInfo);
						//한번 출력을 하면 1초동안 지연시켰다가 다시 setText호출함
						Thread.sleep(1000);
						//아직 반복문이 끝나지 않았으므로 다시 위로 올라가서 다시
						//시간 정보를 while문 안에서 읽고 timeInfo변수에 다시
						//시간 정보를 담음
					}//////////////////////////end of Inner while
				}//////////////////////////////end of outer while
			}catch (InterruptedException ie) {
					ie.printStackTrace();
			}catch (Exception e) {
					e.printStackTrace();
			}finally {
				 try {
						in.close();
						out.close();
						tc2.client.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			
		}
	
}
