package com.network2_b;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TalkClientThread extends Thread {
	//TalkClientThread에서 TalkClient 원본을 참조하기 위해서 선언
	//생성자에서 초기화를 함
	TalkClientVer2 tc2 = null;
	
	public TalkClientThread(TalkClientVer2 tc2) {
		this.tc2 = tc2;
	}
	
	public SimpleAttributeSet makeAttribute(String fontColor) {
		SimpleAttributeSet sas = new SimpleAttributeSet();
		sas.addAttribute(StyleConstants.ColorConstants.Foreground, new Color(Integer.parseInt(fontColor)));
		return sas;
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
				msg = (String)tc2.ois.readObject();//TalkClient 에서 ois = new ObjectInputStream(mySocket.getInputStream());
				StringTokenizer st = null;
				int protocol = 0;
				if(msg!=null) {
					st = new StringTokenizer(msg,Protocol.seperator);
					protocol = Integer.parseInt(st.nextToken());
				}
				switch(protocol) {
					case Protocol.WAIT:{
						String nickName = st.nextToken();
						String state = st.nextToken();
						Vector<String> v_nick = new Vector<>();
						v_nick.add(nickName);
						v_nick.add(state);
						tc2.wr.dtm_wait.addRow(v_nick);
						//테이블 스크롤바 자동 위치 변경
						tc2.wr.jsp_wait.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
							@Override
							public void adjustmentValueChanged(AdjustmentEvent e) {
								JScrollBar jsb = (JScrollBar)e.getSource();
								jsb.setValue(jsb.getMaximum());
							}
						});
					}break;
					case Protocol.ROOM_CREATE:{
						String roomTitle = st.nextToken();
						String currentNum = st.nextToken();
						Vector<String> v_room = new Vector<>();
						v_room.add(roomTitle);
						v_room.add(currentNum);
						tc2.wr.dtm_room.addRow(v_room);
						tc2.wr.jsp_room.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
							@Override
							public void adjustmentValueChanged(AdjustmentEvent e) {
								JScrollBar jsb = (JScrollBar)e.getSource();
								jsb.setValue(jsb.getMaximum());
							}
						});
					}break;
					case Protocol.ROOM_LIST:{
						String roomTitle = st.nextToken();
						String currentNum = st.nextToken();
						Vector<String> v_room = new Vector<>();
						v_room.add(roomTitle);
						v_room.add(currentNum);
						tc2.wr.dtm_room.addRow(v_room);
						tc2.wr.jsp_room.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
							@Override
							public void adjustmentValueChanged(AdjustmentEvent e) {
								JScrollBar jsb = (JScrollBar)e.getSource();
								jsb.setValue(jsb.getMaximum());
							}
						});
					}break;
					case Protocol.ROOM_IN:{
/*
 * 닉네임을 읽어서 Vector 담기 -> dtm_name추가 addRow(v)\
 * 화면(jtp_display) XXX님이 입장하였습니다.
 */
						String nickName = st.nextToken();
						//입장한 사람의 이름을 Vector에 담기 위해
						Vector<String> v_name = new Vector<>();
						//실제 벡터에 추가하는 부분
						v_name.add(nickName);
						//마지막으로 dtm클래스에 이름 추가하기
						tc2.mr.dtm_name.addRow(v_name);
						//화면에 메시지 출력하는 부분
						SimpleAttributeSet sas = makeAttribute();
						try {
							tc2.mr.sd_display.insertString(tc2.mr.sd_display.getLength()
													,nickName+"님이 입장하였습니다.\n"
													,sas);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}break;
					case Protocol.MESSAGE:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						String imgChoice = "";
						while(st.hasMoreTokens()) {
							imgChoice = st.nextToken();
						}
						MutableAttributeSet attr1 = new SimpleAttributeSet();
						if(!imgChoice.equals("default")) {//이모타콘 메세지 일때
							int i=0;
							for(i=0;i<tc2.mr.imo.imgFiles.length;i++) {
								if(tc2.mr.imo.imgFiles[i].equals(imgChoice)) {
									StyleConstants.setIcon(attr1,new ImageIcon(tc2.mr.imo.imgPath+tc2.mr.imo.imgFiles[i]));
									try {
										tc2.mr.sd_display.insertString(tc2.mr.sd_display.getLength(),"\n", attr1);//이모티콘
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						}
						else if(imgChoice.equals("default")){//일반 메세지 일때
							try {
								tc2.mr.sd_display.insertString(tc2.mr.sd_display.getLength(), "["+nickName+"]"+message+"\n", null);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
//						tc2.mr.jtp_display.append("["+nickName+"]"+message+"\n");
						tc2.mr.jtp_display.setCaretPosition(tc2.mr.sd_display.getLength());
					}break;
					case Protocol.WHISPER:{
						String fromName = st.nextToken();
						String toName = st.nextToken();
						String message = st.nextToken();
						try {
							tc2.mr.sd_display.insertString(tc2.mr.sd_display.getLength()
									,fromName+" 님이"+toName+" 님에게"+message+"\n",null);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}break;
					case Protocol.CHANGE:{
						//대화내용 잘라서 받기
						//테이블 대화명 변경하기
						//변경된 대화명 메세지 출력
						//채팅창 타이틀도 변경처리
						String nickName = st.nextToken();
						String afterName = st.nextToken();
						String msg1 = st.nextToken();
						for(int i=0;i<tc2.mr.dtm_name.getRowCount();i++) {
							//기존 대화명 가져오기
							String currentName = (String)tc2.mr.dtm_name.getValueAt(i, 0);
							if(currentName.equals(nickName)) {
								tc2.mr.dtm_name.setValueAt(afterName, i, 0);
								break;
							}
						}
							try {
								//첫번째는 이모티콘 때문에 위치를 얻음
								//두번째는 출력할 메세지
								//세번째는 스타일 적용하기 - 글꼴, 글자크기, 글자색
								tc2.mr.sd_display.insertString(tc2.mr.sd_display.getLength()
										,msg1+"\n",null);
							} catch (Exception e) {
								// TODO: handle exception
							}
							tc2.mr.jtp_display.setCaretPosition(tc2.mr.sd_display.getLength());//줄바꿈처리
							if(nickName.equals(tc2.nickName)) {
								tc2.setTitle(afterName+" 님의 대화명");
								tc2.nickName = afterName;
							}
					}break;
				}///////////////////end of switch
			} catch (Exception e) {
				e.printStackTrace();
			}///////////////////////end of try..catch
		}///////////////////////////end of while
	}///////////////////////////////end of run
}
