package com.network1_b;

import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.text.SimpleAttributeSet;

public class TalkClientThread extends Thread {
	//TalkClientThread에서 TalkClient 원본을 참조하기 위해서 선언
	//생성자에서 초기화를 함
	TalkClient tc = null;
	public TalkClientThread(TalkClient tc) {
		this.tc = tc;
	}
	public SimpleAttributeSet makeAttribute() {
		SimpleAttributeSet sas = new SimpleAttributeSet();
		return sas;
	}
	public void run() {
		String msg = null;
		boolean isStop = false;
		while(!isStop) {
			try {
				msg = (String)tc.ois.readObject();//TalkClient 에서 ois = new ObjectInputStream(mySocket.getInputStream());
				StringTokenizer st = null;
				int protocol = 0;
				if(msg!=null) {
					st = new StringTokenizer(msg,Protocol.seperator);
					protocol = Integer.parseInt(st.nextToken());
				}
				switch(protocol) {
					case Protocol.ROOM_IN:{
/*
 * 닉네임을 읽어서 Vector 담기 -> dtm_name추가 addRow(v)\
 * 화면(jta_display) XXX님이 입장하였습니다.
 */
						String nickName = st.nextToken();
						//입장한 사람의 이름을 Vector에 담기 위해
						Vector<String> v_name = new Vector<>();
						//실제 벡터에 추가하는 부분
						v_name.add(nickName);
						//마지막으로 dtm클래스에 이름 추가하기
						tc.dtm_name.addRow(v_name);
						//화면에 메시지 출력하는 부분
						tc.jta_display.append(nickName+"님이 입장하였습니다.\n");
						tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
					}break;
					case Protocol.MESSAGE:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						tc.jta_display.append("["+nickName+"]"+message+"\n");
						tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
					}break;
					case Protocol.WHISPER:{
						String youName = st.nextToken();
						String message = st.nextToken();
						int index = Integer.parseInt(st.nextToken());
						tc.jta_display.append("["+youName+"]에게 :"+message+"\n");
						tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
					}
				}///////////////////end of switch
			} catch (Exception e) {
				e.printStackTrace();
			}///////////////////////end of try..catch
		}///////////////////////////end of while
	}///////////////////////////////end of run
}
