package com.team07;

import java.sql.Connection;
import java.sql.DriverManager;
import com.util.DBConnectionMgr;

public class QwerServer {
	//오라클서버가 제공하는 드라이버 클래스 정보를 담기
	public static final String _DRIVER  = "oracle.jdbc.driver.OracleDriver";
	public static final String _URL	 = "jdbc:oracle:thin:@192.168.0.22:1521:orcl11";
	public static final String _USER 	 = "qwer";
	public static final String _PW		 = "1234";
	
	private static QwerServer dbMgr = null;
	public static QwerServer getInstance() {
		if(dbMgr==null) {
			dbMgr = new QwerServer();
		}
		return dbMgr;
	}
	public static Connection getConnetion() {
		Connection con = null;
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
		}catch(ClassNotFoundException ce) {
			System.out.println("_DRIVER 찾을 수 없어");
		}catch(Exception e) {
			System.out.println("Oracle서버 연결 실패");
		}
		return con;
	}
	
}
