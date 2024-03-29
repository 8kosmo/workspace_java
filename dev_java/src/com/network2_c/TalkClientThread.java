package com.network2_c;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
					//정보가져오기
						String roomTitle = st.nextToken();
						String current = st.nextToken();
						String nickName = st.nextToken();
					//단톡인원 정보 갱신처리 - 단톡명을 비교하여 갱신처리
//						JOptionPane.showMessageDialog(tc2, "인원수갱신");
						for(int i=0;i<tc2.wr.jtb_room.getRowCount();i++) {
							if(roomTitle.equals(tc2.wr.dtm_room.getValueAt(i, 0))) {//1번째 컬럼 0
								//Param 1)인원수 2) 로우의 값 3)두번째 컬럼
								//DefaultTableModel이 실제 값을 가질수 있는 클래스
								//JTable은 화면만 제공할 뿐
								//JScrollPane은 속지이고 스크롤바만 제공
								tc2.wr.dtm_room.setValueAt(current, i, 1);//2번째 컬럼1
								break;
							}
						}
					//대기실 위치 갱신 - 닉네임을 비교하여 갱신처리
//						JOptionPane.showMessageDialog(tc2, "대기실 위치 갱신");
						//테이블의 로우수 만큼 카운트 - 3명이면 3번 반복
						for(int i=0;i<tc2.wr.jtb_wait.getRowCount();i++) {
//							JOptionPane.showMessageDialog(tc2, nickName+"__"+(String)tc2.wr.dtm_wait.getValueAt(i, 0));
							//내 대화명과 dtm_wait(왼쪽)에 있는 데이터 중에서 첫번째(대화명)과 같은지 비교함
							//변수 i는 로우값이고 0은 컬럼정보니까 대화명"무사".equals("무사")
							if(nickName.equals((String)tc2.wr.dtm_wait.getValueAt(i, 0))) {
								tc2.wr.dtm_wait.setValueAt(roomTitle, i, 1);
							}
						}
					//MessageRoom추가
						//단톡명을 누른 사람만 화면 이동처리 - MessageRoom
						//tc2.nickName - 내 대화명이고, nickName은 서버에서 전송된 대화명이니까
						//이 둘을 비교해서 같니?
						if(tc2.nickName.equals(nickName)) {
							//WaitRoom화면에서 MessageRoom화면으로 이동처리
							tc2.tp.setSelectedIndex(1);
							//MessageRoom화면의 오른쪽 테이블에 내 대화명을 추가
							for(int x=0;x<tc2.wr.jtb_wait.getRowCount();x++) {
								if(roomTitle.equals(tc2.wr.dtm_wait.getValueAt(x, 1))) {
									//내 정보는 WaitRoom에 dtm_wait에서 이름을 가져와서 배열에 담기
									String imsi[] = {(String)tc2.wr.dtm_wait.getValueAt(x, 0)};
									tc2.mr.dtm_name.addRow(imsi);
								}
							}
						}
					}break;
					case Protocol.ROOM_INLIST:{
						String roomTitle = st.nextToken();
						String currentNum = st.nextToken();
						String nickName = st.nextToken();
						Vector<String> v_room = new Vector<>();
						v_room.add(nickName);
						tc2.mr.dtm_name.addRow(v_room);
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
									StyleConstants.setIcon
									(attr1,new ImageIcon(tc2.mr.imo.imgPath+tc2.mr.imo.imgFiles[i]));
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
