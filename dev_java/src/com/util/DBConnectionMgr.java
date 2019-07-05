package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
 * 오라클은 동시 접속자들을 세션으로 관리하는데
 * 일정 개수의 세션을 넘어서면 서버 접속 불가 - 연결불가
 * 자바단에서 사용한 자원 반드시 반납할 것. - 자바튜닝 권고사항
 * 물리적으로 떨어져 있는 오라클 서버에 접속할 때 - Connection
 * 연결통로를 통해서 DML구문을 가져가고 요청한다.
 * 자바에서 PreparedStatement, CallableStatment, ResultSet 로 요청한다.
 * select => 트랜잭션처리대상이 아님 - 테이블에 변화가  없다.
 * pstmt.executeQuery(); 리턴타입:ResultSet -> Cursor조작 rs.next(), rs.isLast(), rs.absolute(3);
 * insert, update, delete => 트랜잭션 처리 대상 - 물리적인 변화
 * pstmt.executeUpdate(); 리턴타입:int
 */
public class DBConnectionMgr {
	public static final String _DRIVER   = "oracle.jdbc.driver.OracleDriver";
	//1)드라이버 연결방식(thin:멀티 tier에서 사용하는데 적합, oci)
	//2)서버의 네트워크정보 - 1521(다폴트) -SID명
	//3)user,pw
	public static final String _URL	 	 = "jdbc:oracle:thin:@192.168.0.22:1521:orcl11";
	public static final String _USER 	 = "scott";
	public static final String _PW		 = "tiger";
	
	
	//하나의 객체로 사용하기 위해 static으로 선언하였다.
	static DBConnectionMgr dbMgr = null;
	//EmpJDBC.java에서 직접 인스턴스화 하지 않는다.(결합도가 낮아진다)
	//복제본 아닌 원본 하나를 사용하기 위해서 null체크 객체 생성
	public static DBConnectionMgr getInstance() {
		if(dbMgr==null) {
			dbMgr = new DBConnectionMgr();
		}
		return dbMgr;
	}
	
	//연결통로 - Tomcat서버와 오라클서버 연결
	public static Connection getConnection() {
		Connection con = null;
		try {
			//외부의 클래스이름을 로딩 - 오라클서버에 대한 정보를 얻기 - ojdbc6.jar build path확인
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
		}catch(ClassNotFoundException ce) {
			System.out.println("드라이버 클래스를 찾을 수 없다");
		}catch(Exception e) {
			System.out.println("오라클 서버 연결 실패");
		}
		return con;
	}
	//사용자 자원 반납하기 - 오라클 세션과 관련 있으므로 반드시 할 것. *finally안에 반드시 처리해주자
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt != null) {
					pstmt.close();
			}
			if(con != null) {
					con.close();
			}
		}catch (Exception e) {
				// TODO: handle exception
		}
	}
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void freeConnection(Connection con, CallableStatement cstmt) {
		try {
			if(cstmt != null) {
				cstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	//사용한 자원 반납 순서는 생성된 역순으로 반납할 것.
	public void freeConnection(Connection con, CallableStatement cstmt, ResultSet rs) {
		try {//rs -> cstmt -> con 순으로 close한다.
			if(rs != null) {
				rs.close();
			}
			if(cstmt != null) {
				cstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
