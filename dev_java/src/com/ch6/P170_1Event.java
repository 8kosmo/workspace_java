package com.ch6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class P170_1Event implements ActionListener {
	P170_1 p170_1 = null;
	public P170_1Event(P170_1 p170_1) {
		this.p170_1 = p170_1;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		if("전송".equals(label)) {
			System.out.println("전송 버튼 클릭2");	
		    p170_1.jbtn.setText("보내기");
		}
		    else if("보내기".equals(label)){
			    System.out.println("보내기");
		    }
	}
}
