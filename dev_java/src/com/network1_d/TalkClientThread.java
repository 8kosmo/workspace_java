package com.network1_d;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TalkClientThread extends Thread {
	//TalkClientThread에서 TalkClient 원본을 참조하기 위해서 선언
	//생성자에서 초기화를 함
	TalkClient tc = null;
	
	public TalkClientThread(TalkClient tc) {
		this.tc = tc;
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
 * 화면(jtp_display) XXX님이 입장하였습니다.
 */
						String nickName = st.nextToken();
						//입장한 사람의 이름을 Vector에 담기 위해
						Vector<String> v_name = new Vector<>();
						//실제 벡터에 추가하는 부분
						v_name.add(nickName);
						//마지막으로 dtm클래스에 이름 추가하기
						tc.dtm_name.addRow(v_name);
						//화면에 메시지 출력하는 부분
						SimpleAttributeSet sas = makeAttribute();
						try {
							tc.sd_display.insertString(tc.sd_display.getLength()
													,nickName+"님이 입장하였습니다.\n"
													,sas);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}break;
					case Protocol.MESSAGE:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						String imgChoice = st.nextToken();
						String fontColor = st.nextToken();
						MutableAttributeSet attr1 = new SimpleAttributeSet();
						if(!imgChoice.equals("default")) {//이모타콘 메세지 일때
							int i=0;
							for(i=0;i<tc.imo.imgFiles.length;i++) {
								if(tc.imo.imgFiles[i].equals(imgChoice)) {
									StyleConstants.setIcon(attr1,new ImageIcon(tc.imo.imgPath+tc.imo.imgFiles[i]));
									try {
										tc.sd_display.insertString(tc.sd_display.getLength(),"\n", attr1);//이모티콘
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						}
						else if(imgChoice.equals("default")){//일반 메세지 일때
							SimpleAttributeSet sas = makeAttribute(fontColor);
							try {
								tc.sd_display.insertString(tc.sd_display.getLength(), "["+nickName+"]"+message+"\n", sas);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
//						tc.jtp_display.append("["+nickName+"]"+message+"\n");
						tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
					}break;
					case Protocol.WHISPER:{
						String fromName = st.nextToken();
						String toName = st.nextToken();
						String message = st.nextToken();
						try {
							tc.sd_display.insertString(tc.sd_display.getLength()
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
						for(int i=0;i<tc.dtm_name.getRowCount();i++) {
							//기존 대화명 가져오기
							String currentName = (String)tc.dtm_name.getValueAt(i, 0);
							if(currentName.equals(nickName)) {
								tc.dtm_name.setValueAt(afterName, i, 0);
								break;
							}
						}
							try {
								//첫번째는 이모티콘 때문에 위치를 얻음
								//두번째는 출력할 메세지
								//세번째는 스타일 적용하기 - 글꼴, 글자크기, 글자색
								tc.sd_display.insertString(tc.sd_display.getLength()
										,msg1+"\n",null);
							} catch (Exception e) {
								// TODO: handle exception
							}
							tc.jtp_display.setCaretPosition(tc.sd_display.getLength());//줄바꿈처리
							if(nickName.equals(tc.nickName)) {
								tc.setTitle(afterName+" 님의 대화명");
								tc.nickName = afterName;
							}
					}break;
					case Protocol.ROOM_OUT:{
						String nickName = st.nextToken();
						String msg1 = st.nextToken();
						try {
							tc.sd_display.insertString(tc.sd_display.getLength(), msg1+"\n", null);
						} catch (Exception e) {
							// TODO: handle exception
						}
						//dtm에서 위 사람의 닉네임을 제거하기
						for(int i=0;i<tc.dtm_name.getRowCount();i++) {
							//dtm에 있는 닉네임 가져오기
							String temp = (String)tc.dtm_name.getValueAt(i, 0);
							if(temp.equals(nickName)) {
								tc.dtm_name.removeRow(i);
								break;
							}
						}
					}break;
				}///////////////////end of switch
			} catch (Exception e) {
				e.printStackTrace();
			}///////////////////////end of try..catch
		}///////////////////////////end of while
	}///////////////////////////////end of run
}
