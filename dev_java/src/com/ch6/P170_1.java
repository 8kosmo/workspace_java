package com.ch6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
/*
 * A is a B관계에 있다면 서로 상속처리한다.
 * 상속받을 땐 하위클래스를 상속받는 것이 더 많은 걸 누릴 수 있다.
 * ActionListener는 이벤트 처리를 담당하는 인터페이스
 * 인터페이스는 추상메소드만 가지고 있다. -왜냐면 기능을 결정할 수 없어서....
 * 구현하는 클래스가 각각 다르기 때문에
 * 어플리케이션이 배포되는 디바이스가 다르니깐
 * ActionListener를 implements한 클래스가 구현체 클래스이다.
 */
public class P170_1 extends JFrame implements ActionListener{
	//JFrame Jt = new JFrame();
	String title;
	JButton jbtn = new JButton("전송");
	//public title(String title) {
		
	//}
	public P170_1() {
		initDisplay();
		//this.title ;
	}//디폴트생성자
	public P170_1(String title) {
		this.title = title;//this.title은 전역변수 title, title은 P170(파라미터 title)
		initDisplay();
	}
	public void initDisplay() {
		//디폴트생성자는 없으면 JVM이 대신 추가해줌.
		//생성자가 하나라도 있으면 대신 해주지 않음.
		P170_1Event pEvent = new P170_1Event(this);
		jbtn.addActionListener(pEvent);
		this.add("North",jbtn);
		this.setTitle(title);//this.setXXX은 P170클래스가 JFrame의 하위클래스이기 때문에 상위클래스인
		this.setSize(500, 600);//JFrame의 메소드도 this.XXX으로 사용 할 수 있다.
		this.setVisible(true);
	}
	public static void main(String[] args) {
		P170_1 p = new P170_1("이렇게 하는게 맞니?");
		//p.initDisplay();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		if("전송".equals(label))
			System.out.println("전송 버튼 클릭");
	}

}
