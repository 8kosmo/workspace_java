package com.network1_a;

import java.io.PrintWriter;
import java.util.Calendar;

public class ServerThread extends Thread {
	TimeServerVer2 ts2 = null;
	public ServerThread(TimeServerVer2 ts2) {
		this.ts2 = ts2;
		//아래 코드에 대해 생각해 보기
		ts2.threadList.add(this);
	}
	//서버측에서는 현재 시간정보를 계산해서 그 정보를 클라이언트측에 제공함.
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
	}///////////////////////end of currentTime(){}
	//Thread는 경합으로 인해 항시 인터셉트가 발생할 수 있으므로 
	//반드시 예외처리해야함
	@Override
	public void run() {
		boolean isOk = false;
		PrintWriter out = null;
		try {
			out = new PrintWriter(ts2.socket.getOutputStream(),true);
			while(!isOk) {
				out.println(currentTime());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
					System.out.println("인터셉트를 당한 경우...");
				}
			}//////////////////end of while
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
