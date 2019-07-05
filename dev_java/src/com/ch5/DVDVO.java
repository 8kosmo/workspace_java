package com.ch5;
//도서에 대한 정보를 담을 수 있는 클래스임
//쓰기와 일기
public class DVDVO {
	private String D_name	= null;//이름
	private String D_actor  = null;//주연배우
	private String D_open	= null;//개봉일자
	public DVDVO() {} //생성자가 하나라도 있으면 디폴트 생성자뿐이다.
	public DVDVO(String D_name,String D_actor,String D_open) {
		this.D_name  = D_name;//전역변수 D_name에 DVDVO안에 전역변수 D_name로 초기화
		this.D_actor = D_actor;
		this.D_open  = D_open;
	}
	public String getD_name() {
		return D_name;
	}
	public void setD_name(String d_name) {
		D_name = d_name;
	}
	public String getD_actor() {
		return D_actor;
	}
	public void setD_actor(String d_actor) {
		D_actor = d_actor;
	}
	public String getD_open() {
		return D_open;
	}
	public void setD_open(String d_open) {
		D_open = d_open;
	}
}
