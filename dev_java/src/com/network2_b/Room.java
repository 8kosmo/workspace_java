package com.network2_b;

import java.util.List;
import java.util.Vector;
/*
 * 단톡방에 관련된 정보를 관리
 * List 추가
 * 1)톡방에 참여하는 사람들에 대한 스레드
 * 2)톡방에 참여하는 사람들 목록관리
 */
public class Room {
	List<TalkServerThread>userList = new Vector<>();
	List<String>		  nameList = new Vector<>();
	String title = null;//단톡방 이름
	String state = null;//대기상태, 참여중...
	int max		= 0;//최대인원
	int current = 0;//현재인원
	public Room() {
		
	}
	public Room(String title, int current) {
		this.title = title;
		this.current = current;
	}
	public Room(String title,String state , int current) {
		this.title = title;
		this.state = state;
		this.current = current;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
}
