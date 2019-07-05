package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DVDConnectionMgr {
	public static final String _DRIVER		 = "oracle.jdbc.driver.OracleDriver";
	public static final String _URL	 		 = "jdbc:oracle:thin:@192.168.0.22:1521:orcl11";
	public static final String _USER 		 = "qwer";
	public static final String _PW			 = "1234";
	
	static DVDConnectionMgr dvdMgr = null;
	
	public static DVDConnectionMgr getInstance() {
		if(dvdMgr==null) {
			dvdMgr = new DVDConnectionMgr();
		}
		return dvdMgr;
	}
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection
					(_URL, _USER, _PW);
		} catch (ClassNotFoundException ce) {
			System.out.println("드라이버 클래스를 찾을 수 없다.");
		} catch(Exception e) {
			System.out.println("오라클 서버 연결 실패");
		}
		return con;
	}
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void freeConnection(Connection con, CallableStatement cstmt) {
		try {
			if(cstmt!=null) {
				cstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void freeConnection(Connection con, CallableStatement cstmt, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(cstmt!=null) {
				cstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}	
