package com.dvdTest;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class DVDManager extends JPanel {
	String cols[] = {"DVD코드","제목","주연배우"};
	String data[][] = new String[3][3];
	DefaultTableModel dtm_dvd = new DefaultTableModel(data,cols);                   //**
	//생성자의 파라미터에 DefaultTableModel 주소번지를 넘겨서 화면과 테이블을 동기화     			  	//**
	JTable			  jt_dvd  = new JTable(dtm_dvd);                                //**
	JTableHeader	  jth_dvd = jt_dvd.getTableHeader();                            //**
	JScrollPane		  jsp_dvd = new JScrollPane(jt_dvd);                            //**
	public DVDManager() {
		initDisplay();
	}
	public void initDisplay() {
		this.setLayout(new BorderLayout());
		this.add("Center",jsp_dvd);
		this.setSize(680, 480);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
	}
}
