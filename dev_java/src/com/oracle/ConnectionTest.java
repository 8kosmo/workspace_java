package com.oracle;

import java.sql.Connection;

import com.util.DBConnectionMgr;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection con = null;
		DBConnectionMgr dbMgr2 = new DBConnectionMgr();
		System.out.println("dbMgr2의 주소번지는:"+dbMgr2);
		DBConnectionMgr dbMgr3 = DBConnectionMgr.getInstance();
		System.out.println("dbMgr3의 주소번지는:"+dbMgr3);
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		System.out.println("dbMgr의 주소번지는:"+dbMgr);
		con = dbMgr.getConnetion();
		System.out.println(con);
	}

}
