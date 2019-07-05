package com.network2_c;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class ChatDao {
	Connection					con 	= null;
	CallableStatement			cstmt	= null;
	OracleCallableStatement		ocstmt	= null;
	ResultSet					rs 		= null;
	DBConnectionMgr				dbMgr	= null;
	public String login(String mem_id, String mem_pw) {
		String nick = null;
		try {
			con = DBConnectionMgr.getConnection();
			cstmt = con.prepareCall("{call proc_chatlogin(?,?,?)}");
			cstmt.setString(1, mem_id);
			cstmt.setString(2, mem_pw);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
			//실행 요청 메소드 호출
			cstmt.execute();
			ocstmt = (OracleCallableStatement)cstmt;
			rs = ocstmt.getCursor(3);
			while(rs.next()) {
				nick = rs.getString("mem_nick");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nick;
	}
}
