package com.team07;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DvdDisplay extends JFrame {
	JButton jbtn_mem = new JButton("회원정보");
	JButton jbtn_dvd = new JButton("DVD관리");
	JButton jbtn_ecnt = new JButton("매출관리");
	JPanel jp_main = new JPanel();
	public void initDisplay() {
		this.setSize(300,100);
		this.setVisible(true);
		this.add(jp_main);
		jp_main.setLayout(new FlowLayout());
		jp_main.add(jbtn_mem);
		jp_main.add(jbtn_dvd);
		jp_main.add(jbtn_ecnt);
	}
}
