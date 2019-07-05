package com.dvd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DVDManagerView extends JFrame{
	public DVDManagerView() {}
	//선언부 
	MemberView memberview = null;
	DVDManagerView dvdmanagerview = null;
	JFrame jf_dvd = new JFrame();
	//MenuBar
	JMenuBar 	jmb_dvd 	= new JMenuBar();
	JMenu    	jm_member 	= new JMenu("회원관리");
		JMenuItem 	jmi_ins  	= new JMenuItem("회원등록");
		JMenuItem 	jmi_upd  	= new JMenuItem("회원정보수정");
		JMenuItem 	jmi_del  	= new JMenuItem("회원탈퇴");
	JMenu    	jm_rent 	= new JMenu("대여관리");
		JMenuItem	jmi_drent	= new JMenuItem("일별매출");
		JMenuItem	jmi_mrent	= new JMenuItem("월별매출");
	JMenu    	jm_dvd 		= new JMenu("DVD관리");
		JMenuItem	jmi_dvdins	= new JMenuItem("DVD등록");
		JMenuItem	jmi_dvddel	= new JMenuItem("DVD삭제");
	//SearchBar
	String searchLabel[] = {"회원목록","DVD목록","대여목록"};
	JComboBox jcb_search = new JComboBox(searchLabel);
	JTextField jtf_keyword = new JTextField("검색할 키워드 입력하세요",47);
	JButton fbtn_search = new JButton("조회");
	//FlowLayout
	JPanel jp_north = new JPanel();//GridLayout써서 두개 영역쪼갬.
	JPanel jp_north_first = new JPanel();//검색기 추가
	JPanel jp_north_second = new JPanel();//기존 버튼 3개 이관
	JButton jbtn_memberdetail = new JButton("회원상세조회");
	JButton jbtn_dvddetail = new JButton("DVD상세조회");
	JButton jbtn_quit = new JButton("나가기");
	//JTable
	String cols[] = {"ID","이름","생년월일","성별","주소","가입일","적립포인트","미납금"};//테이블 헤더에 들어갈 이름들 담기
	String data[][] = new String[0][8];//오라클서버에서 조회한 결과를 담을 2차배열 선언
	DefaultTableModel dtm_mem = new DefaultTableModel(data,cols);
	JTable jt_mem = new JTable(dtm_mem);
	JScrollPane jsp_mem = new JScrollPane(jt_mem
			               ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			               ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	//화면처리구현
	public void initDisplay() {
//===============================================title		
		jf_dvd.setTitle("DVD대여관리시스템  Ver1.0");
//===============================================MenuBar
		jf_dvd.setJMenuBar(jmb_dvd);
		
		jmb_dvd.add(jm_member);
			jm_member.add(jmi_ins);
			jm_member.add(jmi_upd);
			jm_member.add(jmi_del);
		jmb_dvd.add(jm_dvd);
			jm_dvd.add(jmi_dvdins);
			jm_dvd.add(jmi_dvddel);
		jmb_dvd.add(jm_rent);
			jm_rent.add(jmi_drent);
			jm_rent.add(jmi_mrent);
//===============================================JFrame 나누기			
		jf_dvd.add("North",jp_north);
		jf_dvd.add("Center",jsp_mem);
			jp_north.setLayout(new GridLayout(2,1));
			jp_north.add(jp_north_first);			
			jp_north.add(jp_north_second);
//================================================("North",jp_north)
		jp_north_first.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north_first.add(jcb_search);
		jp_north_first.add(jtf_keyword);
		jp_north_first.add(fbtn_search);
			jp_north_second.setLayout(new FlowLayout(FlowLayout.RIGHT));
			jp_north_second.add(jbtn_memberdetail);
			jp_north_second.add(jbtn_dvddetail);		
			jp_north_second.add(jbtn_quit);
			
			jf_dvd.setSize(700, 550);
			jf_dvd.setVisible(true);
//================================================("Center",jsp_mem)
		jt_mem.getTableHeader().setReorderingAllowed(false);//테이블 헤더 위치 변경 금지하기		
		jt_mem.getTableHeader().setResizingAllowed(false);//헤더 크기 변경 금지
		jt_mem.getTableHeader().setBackground(new Color(130,160,160));//테이블 헤더 배경색 변경
		jt_mem.getTableHeader().setForeground(Color.white);//테이블 헤더 글자색 변경
		jt_mem.getColumnModel().getColumn(0).setPreferredWidth(70);//테이블 컬럼 폭지정하기
		jt_mem.getColumnModel().getColumn(1).setPreferredWidth(70);
		jt_mem.getColumnModel().getColumn(2).setPreferredWidth(70);
		jt_mem.getColumnModel().getColumn(3).setPreferredWidth(30);
		jt_mem.getColumnModel().getColumn(4).setPreferredWidth(150);
		jt_mem.getColumnModel().getColumn(5).setPreferredWidth(90);
		jt_mem.getColumnModel().getColumn(6).setPreferredWidth(50);
		jt_mem.getColumnModel().getColumn(7).setPreferredWidth(50);
		jt_mem.repaint();
		jbtn_quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitActionPerformed(e);}	
			});
		jmi_ins.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			memberInsert(e);
		}
	});
		jmi_upd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			memberUpdate(e);
		}
	});
		jtf_keyword.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//setText("");
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		refreshData();
	}//end of initDisplay//end of initDisplay//end of initDisplay//end of initDisplay//end of initDisplay
	protected void memberUpdate(ActionEvent e) {
		String label = e.getActionCommand();
		int index = jt_mem.getSelectedRow();
		if(index<0) {
			JOptionPane.showMessageDialog(this,"수정할 데이터를 선택하세요");
			return;
		}else {
			try {
				jt_mem.clearSelection();
				MemberVO paVO = new MemberVO();
				String u_id = (String)dtm_mem.getValueAt(index,0);
				paVO.setId(u_id);
				paVO.setCommand("detail");
				DVDCtrl dvdctrl = new DVDCtrl();
				MemberVO raVO = dvdctrl.send(paVO);
				memberview = null;
				memberview = new MemberView();
				memberview.set(raVO, label, this, true);
			} catch (Exception e2) {
			}
		}
	}
	protected void memberInsert(ActionEvent e) {
		String label = e.getActionCommand();
		memberview = null;
		memberview = new MemberView();
		memberview.set(null, label, this, true);
	}
	protected void quitActionPerformed(ActionEvent e) {
		jf_dvd.setVisible(false);
	}
	public void refreshData() {
		while(dtm_mem.getRowCount()>0) {
			dtm_mem.removeRow(0);
		}
		DVDCtrl dvdCtrl = new DVDCtrl();
		List<MemberVO> list = dvdCtrl.send_member("select");
		if((list==null)||(list.size()==0)) {
			JOptionPane.showMessageDialog(this, "데이터가 없습니다.");
		}
		else {
			for(int i=0;i<list.size();i++) {
				MemberVO  raVO = list.get(i);
				Vector rowData = new Vector();
				rowData.add(0,raVO.getId());
				rowData.add(1,raVO.getName());
				rowData.add(2,raVO.getBirth());
				rowData.add(3,raVO.getGen());
				rowData.add(4,raVO.getAddress());
				rowData.add(5,raVO.getRegdate());
				rowData.add(6,raVO.getPoint());
				rowData.add(7,raVO.getLate());
				dtm_mem.addRow(rowData);
			}
		}
	}
	//메인메소드
	public static void main(String[] args) {
		DVDManagerView dvdmanagerview = new DVDManagerView();
		dvdmanagerview.initDisplay();
	}
}

