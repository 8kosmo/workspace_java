package com.baseball;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BaseBallGame extends JFrame implements ActionListener{
	//선언부
	//컴터가 채번한 숫자 3개를 담을 배열을 선언하기
	int com[] = new int[3]; //현재 3개방에는 0이 담겨있다.
	//사용자가 입력한 숫자를 담을 배열도 같이 선언하자
	int my[] = new int[3];
    //jf는 전역변수이고 선언 및 초기화 완료
	JFrame 		jf 				= new JFrame();
	//jp_center 속지에 중앙면적 - jta_display
	//jp_center 속지에 남쪽면적 - jtf_user
	//jta_didplay -> jp_center속지 ->JFrame배치
	//jp_center속지의 레이아웃을 BorderLayout설정
	JPanel 		jp_center 		= new JPanel();
	JPanel 		jp_east			= new JPanel();
	JTextArea 	jta_display 	= new JTextArea();
	JTextField 	jtf_user 		= new JTextField();
	//jp_east속지에 버튼 네개 추가 - new GridLayout(4,1)
	JButton jbtn_new			= new JButton("새게임");
	JButton jbtn_dap			= new JButton("정답");
	JButton jbtn_clear			= new JButton("지우기");
	JButton jbtn_exit			= new JButton("나가기");
	//JScrollBar위에 JTextArea 얹기
	JScrollPane jsp_display 	= new JScrollPane(jta_display
												 ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
												 ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	//기회값을 담을 변수 선언
	int count =0;
	//생성자 선언하기
	public BaseBallGame() {
		initDisplay();
		ranCom();
	}
	//새게임을 눌렀을 때 임의의 숫자를 채번하는 메소드 선언해보자.
	public void ranCom() {
		Random r = new Random();
			com[0] = r.nextInt(10);
		    do {
			com[1] = r.nextInt(10);
		    }while(com[0]==com[1]);
		    do {
			com[2] = r.nextInt(10);
		    }while((com[0]==com[2])||(com[1]==com[2]));
	}
	//화면처리부
	public void initDisplay() {
		int width = 400;//지역변수 - 메소드 안에 선언했을 때
		int height = 500;
		this.setSize(width, height);
		String title = "야구숫자게임 - Ver1.0";
		this.setTitle(title);
		this.setVisible(true);
		jp_center.setBackground(Color.orange);
		jp_east.setBackground(Color.black);
		//jframe중앙에 jp_center 속지를 붙이고, 동쪽에 jp_east속지를 붙이자.
		this.add("Center",jp_center);
		jp_east.add(jbtn_new);
		jp_east.add(jbtn_dap);
		jp_east.add(jbtn_clear);
		jp_east.add(jbtn_exit);
		this.add("East",jp_east);
		//속지의 레이아웃을 BorderLayout으로 변경
		jp_center.setLayout(new BorderLayout());
		//jp_east속지 레이아웃을 GridLayout으로 변경
		jp_east.setLayout(new GridLayout(4,1));
		
		jp_center.add("South",jtf_user);
		jp_center.add("Center",jsp_display);
		jtf_user.setBackground(new Color(255,255,200));
		jta_display.setBackground(new Color(255,255,200));
		//버튼의 배경색과 글자색 변경
		jbtn_new.setBackground(new Color(158,255,9));
		jbtn_new.setForeground(new Color(212,212,212));
		jbtn_dap.setBackground(new Color(158,155,9));
		jbtn_dap.setForeground(new Color(212,212,212));
		jbtn_clear.setBackground(new Color(158,9,9));
		jbtn_clear.setForeground(new Color(212,210,212));
		jbtn_exit.setBackground(new Color(158,9,9));
		jbtn_exit.setForeground(new Color(212,87,212));
		//이벤트 소스와 이벤트 처리를 담당하는 클래스 연결
		jtf_user.addActionListener(this);
		jbtn_new.addActionListener(this);
		jbtn_dap.addActionListener(this);
		jbtn_clear.addActionListener(this);
		jbtn_exit.addActionListener(this);
	}
	//판정하는 메소드
	/***********************************************************
	 *@param user - 사용자가 입력한 숫자를 담을 변수
	 *@return - 사용자가 입력한 숫자와 채번한 숫자를 비교해서 힌트문을 반환
	 ***********************************************************/
	public String account(String user) {
		//파라미터로 새로운 숫자를 받아올 때 마다 볼카운트를 다시 해야한다.
		//또 사용자가 입력한 값은 사용자가 JTextField에 숫자를 입력한 후 엔터를 쳤을 때
		//account("256")를 호출해야 하지 않을까?
		int strike = 0;
		int ball = 0;
		int temp = 0;//문자열 256를 숫자 타입으로 형전환하여 담을 변수 선언
		try {
			temp = Integer.parseInt(user);
		}catch(NumberFormatException e) {
			return "int만 입력하세요";
		}
		my[0] = temp/100;
		my[1] = (temp%100)/10;
		my[2] = temp%10;
		//사용자가 입력한 숫자가 256문자열로 넘어오니깐.. my배열에 다시 담아주어야 한다.
		//insert here- my배열의 초기화진행
		//백의 자리 user/100		=2
		//십의 자리 (user%10)/10	=5
		//일의 자리 user%10	=6
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				//너가 입력한 숫자가 com안에 있니? 네-볼
				if(com[i]==my[j]) {
					if(i==j) {
						//혹시 자리까지 똑같은거야?? 네-스트라이크 아니 볼판정
						strike++;
					}// end of strike
					else{
						ball++;
					}// end of ball
				}
			}////////// end of inner for
		}////////////// end of outer for
		if(strike==3) {
			return "정답입니다. 축하합니다.";
		}
		return "	"+strike+"스"+ball+"볼";
	}
//메인메소드-entry point-callback method-내가 호출하는게 아니고 가상머신이 호출하는 메소드
	public static void main(String[] args) {
		BaseBallGame bbg = new BaseBallGame();
		
	}
//callback method - 개발자가 호출하는 것이 아니라, 시스템에서 자동 호출 -enter 쳤을때
@Override
public void actionPerformed(ActionEvent e) {
	Object obj = e.getSource();//이벤트 소스(jtf_user)에 대한 주소번지를 읽기
	System.out.println("obj : "+obj);
	//너 정답을보고 싶은거야?
	if(obj == jbtn_dap) {
		jta_display.append(com[0]+""+com[1]+""+com[2]+"입니다.");
	}
	//너 엔터친거니?
	else if(obj==jtf_user) {
		//System.out.println("제발 그만해");
		String userInput = jtf_user.getText();
		if(userInput.length()!=3) {
			JOptionPane.showMessageDialog(this, "세자리 숫자를 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			jtf_user.setText("");//입력창 초기화
			return;//actionPerformed탈출
		}
		jta_display.append(++count+". "+userInput+account(userInput)+"\n");
		jtf_user.setText("");
		
	}
	//지우기 버튼 누른거야?
	else if(obj==jbtn_clear) {
		jta_display.setText("");
	}
	//새게임 할거니?
	else if(obj==jbtn_new) {
		count = 0;
		ranCom();
		jta_display.append(com[0]+""+com[1]+""+com[2]+"\n");
	}
}
}
