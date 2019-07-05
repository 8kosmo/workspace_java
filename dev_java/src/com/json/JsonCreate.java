package com.json;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.util.DBConnectionMgr;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import teacher.address.AddressVO;

public class JsonCreate {
	//물리적으로 떨어져 있는 서버에 연결통로 확보 
	Connection con = null;
	//오라클 서버에 쿼리문을 전달할 객체 생성
	CallableStatement cstmt = null;
	PreparedStatement pstmt = null;
	//SYS_REFCURSOR를 지원하는 인터페이스 - ojdbc6.jar
	OracleCallableStatement ocstmt = null;
	//오라클 응답을 받아서 커서를 조작
	ResultSet rs = null;
	DBConnectionMgr dbMgr = null;
	public JsonCreate() {
		dbMgr = DBConnectionMgr.getInstance();//객체주입
		try {
			con = dbMgr.getConnection();
			cstmt = con.prepareCall("{call proc_mkaddrtb(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();//executeQuery():ResultSet, excuteUpdate():int
			ocstmt = (OracleCallableStatement)cstmt;
			ResultSet cursor = ocstmt.getCursor(1);//OUT ref_addr
			List<AddressVO> memList = new ArrayList<>();
			AddressVO aVO = null;
			while(cursor.next()) {//true 이면 값이 존재함
				aVO = new AddressVO();
				aVO.setId(cursor.getString("id"));
				aVO.setName(cursor.getString("name"));
				aVO.setAddress(cursor.getString("address"));
				aVO.setHp(cursor.getString("hp"));
				//반복문 안에서 인스턴스화가 진행되므로 오버라이트가 발생함.
				//그 주소번지가 가리키는 정보를 보존하기 위해 List사용함
				memList.add(aVO);
			}
			Gson g = new Gson();//List -> JSON형태로 전환
			String jsonMember = g.toJson(memList);
			System.out.println(jsonMember);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		new JsonCreate();
	}

}
