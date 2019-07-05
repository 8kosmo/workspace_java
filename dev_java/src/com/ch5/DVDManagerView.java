package com.ch5;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.team07.QwerServer;
/*****************************************************************************
 * 학습목표 : 리턴타입, 파라미터, 생성자 선언, 인스턴스화
 * 나는 인스턴스화를 할 때 적절한 생성자를 사용할 수 있다. - 파라미터와 타입 맞추기
 * 나는 인스턴스 변수를 활용하여 메소드를 호출 할 수 있다.
 * 나는 메소드 호출시 파라미터 혹은 리턴타입을 활용할 수 있다.
 * 
 * 오라클서버에 자바언어를 활용하여 접속하기
 * SELECT문 요청
 * 사전필요-SELECT문 작성하고 StringBuilder클래스에 저장
 * 1단계-물리적으로 떨어져 있는 오라클 서버에 연결통로 확보(오라클 드라이버 클래스도 스캔)
 * 2단계-쿼리문을 오라클서버에 전달해줄 클래스가 필요함. PrepaerdStatement
 *      만일 WHERE 절이 있을 경우 파라미터로 사용자가 입력한 값을 넘김.
 * 3단계-오라클서버에게 처리를 요청
 * 		a) SELECT인 경우 - 커서를 조작해야 함.커서이동할 땐 next() 호출-인스턴스화-클래스이름
 *			ResultSet rs = pstmt.executeQuery():ResultSet
 * 		b) INSERT|UPDATE|DELETE - 커서가 필요없음.
 * 			int result = pstmt.executeUpdate():int
 *****************************************************************************/
public class DVDManagerView implements KeyListener, ActionListener, MouseListener{
	//선언부 - 선언부에서 메소드 호출 불가
	//////////////////////////////메뉴바 추가하기 시작///////////////////////
	JMenuBar  jmb_dvd 		= new JMenuBar();
	JMenu     jm_mem 		= new JMenu("회원관리");
	JMenuItem jmi_ins		= new JMenuItem("회원등록");
	JMenuItem jmi_upd		= new JMenuItem("회원정보 수정");
	JMenuItem jmi_del		= new JMenuItem("회원탈퇴");
	JMenu     jm_rent 	    = new JMenu("대여관리");
	JMenu     jm_sales	    = new JMenu("매출관리");
	JMenuItem jmi_DS_data	= new JMenuItem("일별매출");
	JMenuItem jmi_MS_data   = new JMenuItem("월별매출");
	JMenuItem jmi_YS_data 	= new JMenuItem("연별매출");
	JMenu	  jm_dvdm		= new JMenu("DVD관리");
	JMenuItem jmi_dvdd		= new JMenuItem("DVD파손여부");
	JMenuItem jmi_dvdr		= new JMenuItem("DVD대여여부");	
	//////////////////////////////메뉴바 추가하기 끝////////////////////////
	//이 속지에 조회, 입력, 수정, 삭제 버튼 추가하기 - FlowLayout
	JPanel jp_north = new JPanel();
	JButton jbtn_sel = new JButton("조회");
	JButton jbtn_ins = new JButton("입력");
	JButton jbtn_upd = new JButton("수정");
	JButton jbtn_del = new JButton("삭제");
	JFrame jf_dvd = new JFrame();
	//생성자 - 생성자는 절대로 return 타입을 가질 수 없다.
	//테이블 헤더에 들어갈 이름들 담기
	String cols[] = {"주문번호","DVD일련번호","주민번호","대여일","미납일","회수일","대여료","연체료"};
	//오라클서버에서 조회한 결과를 담을 2차배열 선언
	String data[][] = new String[0][8];
	//실제 데이터를 담을 수 있는 클래스 생성하기
	DefaultTableModel dtm_rent = new DefaultTableModel(data,cols);
	//실제 테이블을 그려줄 클래스 생성(화면만, 폼만, 양식만, ....)
	JTable jt_rent = new JTable(dtm_rent);
	//바닥속지 ->JTable ->DefaultTableModel->data, cols 이용
	JScrollPane jsp_rent = new JScrollPane(jt_rent
										  ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
										  ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public DVDManagerView() {}
	StringBuilder sql = new StringBuilder();
	//DVD목록 조회 구현
	public void getDVDList(String dvd_title) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT R_ORNUM ,  ");
	    sql.append("       D_DNO ,    ");
	    sql.append("       M_ID ,     ");
	    sql.append("       R_RDATE ,  ");
	    sql.append("       R_UNP ,    ");
	    sql.append("       R_REC ,    ");
	    sql.append("       R_RPAY ,   ");
	    sql.append("       R_LPAY     ");
	    sql.append("  FROM RENTAL     ");
	    try {
			Class.forName(QwerServer._DRIVER);
			Connection con =
					DriverManager.getConnection(QwerServer._URL
												, QwerServer._USER
												, QwerServer._PW);
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery(); 
			ArrayList<HashMap<String,Object>> list = 
					new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> rmap = null;
			while(rs.next()) {
				rmap = new HashMap<String,Object>();
				rmap.put("R_ORNUM", rs.getString("R_ORNUM"));
				rmap.put("D_DNO", 	rs.getString("D_DNO"));
				rmap.put("M_ID", 	rs.getString("M_ID"));
				rmap.put("R_RDATE", rs.getString("R_RDATE"));
				rmap.put("R_UNP",	rs.getString("R_UNP"));
				rmap.put("R_REC", 	rs.getString("R_REC"));
				rmap.put("R_RPAY", 	rs.getString("R_RPAY"));
				rmap.put("R_LPAY", 	rs.getString("R_LPAY"));
				list.add(rmap);
			}
			Iterator<HashMap<String,Object>> iter = list.iterator();
			Object obj[] = null;
			while(iter.hasNext()) {
				HashMap data = iter.next();
				obj = data.keySet().toArray();
				Vector oneRow = new Vector();
				oneRow.add(data.get(obj[0]));
				oneRow.add(data.get(obj[1]));
				oneRow.add(data.get(obj[2]));
				oneRow.add(data.get(obj[3]));
				oneRow.add(data.get(obj[4]));
				oneRow.add(data.get(obj[5]));
				oneRow.add(data.get(obj[6]));
				oneRow.add(data.get(obj[7]));
				dtm_rent.addRow(oneRow);
			}
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	//화면처리구현
	public void initDisplay() {
		jf_dvd.setTitle("DVD대여관리시스템 Ver1.0");
		jmb_dvd.add(jm_rent);//메뉴바(jmb_dvd)에 jm_rent메뉴를 추가했다.
		jmb_dvd.add(jm_mem);//메뉴바(jm_mem)에 jm_rent메뉴를 추가했다.
		jm_mem.add(jmi_ins);//jm_mem메뉴 하단에 jmi_ins메뉴를 추가했다.
		jm_mem.add(jmi_upd);//jm_mem메뉴 하단에 jmi_upd메뉴를 추가했다.
		jm_mem.add(jmi_del);//jm_mem메뉴 하단에 jmi_del메뉴를 추가했다.
		jmb_dvd.add(jm_sales);//메뉴바(jm_sales)에 jm_rent메뉴를 추가했다.
		jm_sales.add(jmi_DS_data);//jm_sales메뉴 하단에 jmi_DS_data메뉴를 추가했다.
		jm_sales.add(jmi_MS_data);//jm_sales메뉴 하단에 jmi_MS_data메뉴를 추가했다.
		jm_sales.add(jmi_YS_data);//jm_sales메뉴 하단에 jmi_YS_data메뉴를 추가했다.
		jf_dvd.setJMenuBar(jmb_dvd);//메뉴바를 만들었고 그 변수 이름은 jmb_dvd 이다.
		jmb_dvd.add(jm_dvdm);
		jm_dvdm.add(jmi_dvdd);
		jm_dvdm.add(jmi_dvdr);
		jf_dvd.add("North",jp_north);
		jf_dvd.add("Center", jsp_rent);
		jf_dvd.setSize(700, 700);
		jf_dvd.setVisible(true);
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.add(jbtn_sel);
		jp_north.add(jbtn_ins);
		jp_north.add(jbtn_upd);
		jp_north.add(jbtn_del);
	}
	//메인메소드
	public static void main(String[] args) {
		DVDManagerView dvd = new DVDManagerView();
		dvd.initDisplay();
		QwerServer login = QwerServer.getInstance();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
