package com.network2_a;

public class Protocol {
	public static final int WAIT 		= 100;//대기상태
	public static final int ROOM_CREAT 	= 110;//톡방개설
	public static final int ROOM_LIST 	= 120;//톡방이름목록
	public static final int ROOM_IN 	= 130;//톡방 입장
	public static final int ROOM_INLIST = 140;//톡방에 있는 사람목록
	public static final int ROOM_OUT 	= 500;//톡방 나가기
	public static final int MESSAGE 	= 200;//일반 대화
	public static final int WHISPER 	= 210;//1:1
	public static final int CHANGE 		= 300;//대화명변경
	public static final int EXIT 		= 500;//종료
	//메세지 열에서 값에 대한 구분자를 선언
	public static String seperator 		= "|";
}
