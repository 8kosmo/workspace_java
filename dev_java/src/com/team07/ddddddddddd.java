package com.team07;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ch5.DVDManagerView;
import com.team07.QwerServer;
/**********************************************************************
 * 학습목표
 * 나는 인스턴스화를 할 때 적절한 생성자를 사용할 수 있다. - 파라미터와 타입 맞추기
 * 나는 인스턴스 변수를 활용하여 메소드를 호출 할 수 있다.
 * 나는 메소드 호출시 파라미터 혹은 리턴타입을 활용할 수 있다.
 * 
 * 오라클서버에 자바언어를 활용하여 접속하기
 * SELECT문 요청
 * 사전필요 - SELECT문 작성하고 StringBuilder클래스에 저장
 * 1단계- 물리적으로 떨어져 있는 오라클 서버에 연결통로 확보(오라클 드라이버클래스도 스캔)
 * 2단계 - 쿼리문을 오라클서버에 전달해줄 클래스가 필요함. PreparedStatement
 *       만일 where절이 있을 경우 파라미터로 사용자가 입력한 값을 넘김.
 * 3단계 - 오라클서버에게 처리 요청
 *       a)select인 경우 - 커서를 조작해야 함. 커서이동할 땐 next() 호출-인스턴스화-클래스이름
 *         ResultSet rs = pstmt.executeQuery():ResultSet
 *       b)insert|update|delete - 커서가 필요없음.       
 *         int result = pstmt.executeUpdate():int
 **********************************************************************/
public class ddddddddddd implements ActionListener{
	//선언부 - 선언부에서 메소드 호출 불가
	/////////////////////// 메뉴바 추가하기 시작 ////////////////////
	JMenuBar 	jmb_dvd 	= new JMenuBar();
	JMenu    	jm_member 	= new JMenu("회원관리");
	JMenuItem 	jmi_ins  	= new JMenuItem("회원등록");
	JMenuItem 	jmi_upd  	= new JMenuItem("회원정보수정");
	JMenuItem 	jmi_del  	= new JMenuItem("회원탈퇴");
	JMenu    	jm_rent 	= new JMenu("대여관리");
	JMenu    	jm_dvd 		= new JMenu("DVD관리");
	/////////////////////// 메뉴바 추가하기   끝 ////////////////////
	///////////////////////////////////////검색기 추가시작////////
	String searchLabel[] = {"DVD명","주연배우","감독"};
	JComboBox jcb_search = new JComboBox(searchLabel);
	JTextField jtf_keyword = new JTextField("검색할 키워드 입력하세요.",50);
	///////////검색기 추가 끝/////////////////////////////////////
	//이 속지에 조회,입력,수정,삭제 버튼 추가하기 - FlowLayout
	JPanel jp_north = new JPanel();
	JPanel jp_north_first = new JPanel();
	JPanel jp_north_second = new JPanel();
	JButton jbtn_sel = new JButton("조회");
	JButton jbtn_ins = new JButton("입력");
	JButton jbtn_upd = new JButton("수정");
	JButton jbtn_del = new JButton("삭제");
	JFrame jf_dvd = new JFrame();
	//생성자 - 생성자는 절대로 리턴타입을 가질 수 없다.
	//테이블 헤더에 들어갈 이름들 담기
	String cols[] = {"대여번호","DVD명","주연배우","감독","회원명"};
	//오라클서버에서 조회한 결과를 담을 2차배열 선언
	String data[][] = new String[5][5];
	//실제 데이터를 담을 수 있는 클래스 생성하기
	DefaultTableModel dtm_rent = new DefaultTableModel(data,cols);
	//실제 테이블을 그려줄 클래스 생성(화면만, 폼만, 양식만,....)
	JTable jt_rent = new JTable(dtm_rent);
	//바닥속지->JTable->DefaultTableModel->cols, data이용
	JScrollPane jsp_rent = 
			new JScrollPane(jt_rent
			               ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			               ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public ddddddddddd() {}
	//DVD목록 조회 구현
	public void getDVDList(String dvd_title) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT movie.actor ,DVD.DVD_TITLE    ");
	    sql.append("      ,movie.director ,MEMBER.M_NAME ");
	    sql.append("      ,RENTAL.SERIAL_NO              ");
	    sql.append("  FROM dvd, rental, movie, member    ");
	    sql.append(" WHERE dvd.dvd_no = rental.dvd_no    ");
	    sql.append("   AND movie.movie_code = rental.movie_code");
	    sql.append("   AND MEMBER.M_NO = rental.mem_no   ");
	    try {
			Class.forName(QwerServer._DRIVER);
			Connection con = 
					DriverManager.getConnection(QwerServer._URL
							                  , QwerServer._USER
							                  , QwerServer._PW);
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			List<HashMap<String,Object>> list = 
					new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> rmap = null;
			while(rs.next()) {
				rmap = new HashMap<String,Object>();
				rmap.put("actor", rs.getString("actor"));
				rmap.put("dvd_title", rs.getString("dvd_title"));
				rmap.put("director", rs.getString("director"));
				rmap.put("m_name", rs.getString("m_name"));
				rmap.put("serial_no", rs.getString("serial_no"));
				list.add(rmap);
			}
			Iterator<HashMap<String,Object>> iter = list.iterator();
			Object obj[] = null;
			while(iter.hasNext()){
				HashMap data = iter.next();
				obj = data.keySet().toArray();
				Vector oneRow = new Vector();
				oneRow.add(data.get(obj[0]));
				oneRow.add(data.get(obj[1]));
				oneRow.add(data.get(obj[2]));
				oneRow.add(data.get(obj[3]));
				oneRow.add(data.get(obj[4]));
				dtm_rent.addRow(oneRow);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	//화면처리구현
	public void initDisplay() {
		jbtn_sel.addActionListener(this);
		jp_north_first.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north_first.add(jbtn_sel);
		jp_north_first.add(jbtn_ins);
		jp_north_first.add(jbtn_upd);
		jp_north_first.add(jbtn_del);
		jp_north_second.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north_second.add(jcb_search);
		jp_north_second.add(jtf_keyword);
		//테이블 헤더 위치 변경 금지하기
		jt_rent.getTableHeader().setReorderingAllowed(false);
		//테이블 컬럼 폭지정하기
		jt_rent.getColumnModel().getColumn(0).setPreferredWidth(70);
		jt_rent.getColumnModel().getColumn(1).setPreferredWidth(120);
		//테이블 헤더 배경색 변경
		jt_rent.getTableHeader().setBackground(new Color(130,160,160));
		//테이블 헤더 글자색 변경
		jt_rent.getTableHeader().setForeground(Color.white);
		jf_dvd.setTitle("DVD대여관리시스템  Ver1.0");
		jm_dvd.add(jmi_ins);
		jm_dvd.add(jmi_upd);
		jm_dvd.add(jmi_del);
		jmb_dvd.add(jm_dvd);
		jf_dvd.setJMenuBar(jmb_dvd);
		jf_dvd.add("North",jp_north_first);
		jf_dvd.add("North",jp_north_second);
		jf_dvd.add("Center",jsp_rent);
		jf_dvd.setSize(700, 550);
		jf_dvd.setVisible(true);
	}
	//메인메소드
	public static void main(String[] args) {
		ddddddddddd dvd = new ddddddddddd();
		dvd.initDisplay();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbtn_sel) {
			getDVDList("hello");
		}
		else if(e.getSource()==jtf_keyword) {
			jtf_keyword.setText("");
		}
		
	}

}
