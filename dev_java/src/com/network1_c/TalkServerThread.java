package com.network1_c;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

public class TalkServerThread extends Thread {
	TalkServer ts = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String nickName = null; //사용자의 닉네임 담김
	
	public TalkServerThread(TalkServer ts) {
		this.ts = ts;
		try {
			oos = new ObjectOutputStream(ts.client.getOutputStream());
			ois = new ObjectInputStream(ts.client.getInputStream());
			String msg = (String)ois.readObject();
			ts.jta_log.append(msg+"\n");
			//자동으로 스크롤바 이동시켜주기
			ts.jta_log.setCaretPosition(ts.jta_log.getDocument().getLength());
			StringTokenizer st = null;
			if(msg!=null) {
				st = new StringTokenizer(msg,Protocol.seperator);
			}
			st.nextToken();//100
			nickName = st.nextToken();//닉네임담기
			//나 이전에 들어와 있는 친구들에게 메시지 전송하기
			for(TalkServerThread tst:ts.chatList) {
				String currentName = tst.nickName;
				this.send(Protocol.ROOM_IN+Protocol.seperator+currentName);
			}
			//입장한 내 스레드 추가하기
			ts.chatList.add(this);
			//현재 서버에 접속한 모든 사람들에게 메시지 전송하기
			this.broadCasting(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//서버에 접속한 모든 사용자들에게 메세지를 전송 처리함.
	public void broadCasting(String msg) {//200|누가|누구에게|오늘 스터디할까?
		for(TalkServerThread tst:ts.chatList) {//n개만큼 처리한다
			tst.send(msg);//this.send(msg), tst.send(msg) 차이
		}
	}
	public void YouandI(String msg, int index) {
		this.send(msg);
		TalkServerThread tst = ts.chatList.get(index);
		tst.send(msg);
	}
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//run메소드 안에서는 무엇을 구현해야 되는걸까? 듣기
	public void run() {
		boolean isStop = false;
		try {
			run_start://break run_start;
				while(!isStop) {
					String msg = (String)ois.readObject();
					ts.jta_log.append(msg+"\n");//200|나신입|주말에 뭐해?
					ts.jta_log.setCaretPosition(ts.jta_log.getDocument().getLength());
					int protocol = 0;
					StringTokenizer st = null;
					if(msg!=null) {
						st = new StringTokenizer(msg,"|");
						protocol = Integer.parseInt(st.nextToken());
					}
					switch(protocol) {
					case Protocol.MESSAGE:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						String imgChoice = "";
						while(st.hasMoreTokens()) {
							imgChoice = st.nextToken();
						}
						this.broadCasting(Protocol.MESSAGE
										 +Protocol.seperator+nickName
										 +Protocol.seperator+message
										 +Protocol.seperator+imgChoice
										 );
					}break;
                    case Protocol.WHISPER:{
                    	String youName = st.nextToken();
						String message = st.nextToken();
						int index = Integer.parseInt(st.nextToken());
						this.YouandI(Protocol.WHISPER
									+Protocol.seperator+youName
									+Protocol.seperator+message, index);
						
					}break;
                    case Protocol.ROOM_OUT:{
						
					}break;
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
