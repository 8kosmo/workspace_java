package com.address;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AddressBook extends JFrame implements ActionListener {
	SubBook subBook = null;
	static AddressBook aBook = null;
	JPanel jp_north = new JPanel();
	JButton jbtn_ins = new JButton("입력");
	JButton jbtn_upd = new JButton("수정");
	JButton jbtn_del = new JButton("삭제");
	JButton jbtn_det = new JButton("상세조회");
	//헤더 정보를 담을 객체를 추가
	String cols[] = {"아이디","이름","주소","HP"};
	String data[][] = new String[0][4];
	DefaultTableModel dtm_address = new DefaultTableModel(data,cols);
	//데이터를 담을수 있는 클래스가 필요함.
	//Dataset
	JTable jt_address = new JTable(dtm_address);//화면만 제공함. 그리드만 제공. 데이터없다.
	JScrollPane jsp_address = new JScrollPane(jt_address);
	JTableHeader jth_address = jt_address.getTableHeader();//getTableHeader new 안쓰고 생성
	public void initDisplay() {
		jbtn_ins.addActionListener(this);
		jbtn_upd.addActionListener(this);
		jbtn_det.addActionListener(this);
		jp_north.setLayout(new FlowLayout());
		jp_north.add(jbtn_ins);
		jp_north.add(jbtn_upd);
		jp_north.add(jbtn_del);
		jp_north.add(jbtn_det);
		this.add("North",jp_north);
		this.add("Center",jsp_address);
		this.setSize(600, 500);
		this.setVisible(true);
		jth_address.setFont(new Font("맑은고딕",Font.BOLD,18));
		jth_address.setBackground(new Color(22,22,100));
		jth_address.setForeground(Color.white);
		jth_address.setReorderingAllowed(false);
		jth_address.setResizingAllowed(false);
		jt_address.setGridColor(Color.blue);
		jt_address.getColumnModel().getColumn(0).setPreferredWidth(80);
		jt_address.getColumnModel().getColumn(1).setPreferredWidth(100);
		jt_address.getColumnModel().getColumn(2).setPreferredWidth(390);
		jt_address.getColumnModel().getColumn(3).setPreferredWidth(130);
		jt_address.repaint();
	}
	//새로고침 처리 메소드 구현
	public void refreshData() {
		System.out.println("새로고침 처리");
		
	}
	public static void main(String[] args) {
		if(aBook==null) {
			aBook = new AddressBook();
		}
		aBook.initDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String label = ae.getActionCommand();//입력|수정|상세조회
		if("입력".equals(label)) {
			//subBook.set(label,aBook);
			subBook = null;
			subBook = new SubBook(aBook);
			subBook.set(null,label,aBook,true);
		}
		else if("수정".equals(label)) {
			//subBook.set(label,aBook);
			subBook = null;
			subBook = new SubBook(aBook);
			subBook.set(new AddressVO(),label,aBook,false);
		}
		else if("상세조회".equals(label)) {
			subBook = null;
			subBook = new SubBook();
			//문제제기-어!!! 화면그리는 메소드가 사라졌네?
			subBook.set(new AddressVO(),label,aBook,false);
		}
	}

}
