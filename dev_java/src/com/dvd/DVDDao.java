package com.dvd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConnectionMgr;

public class DVDDao implements DVDInterface {
	Connection					con 	= null;
	PreparedStatement			pstmt 	= null;
	ResultSet					rs 		= null;
	DBConnectionMgr				dbMgr	= null;
	
	@Override
	public MemberVO getMemberDetail(MemberVO paVO) {
		dbMgr = DBConnectionMgr.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id,pw,name,birth,gen,address      ");
		sql.append("   FROM dvdmember                         ");
		sql.append("  WHERE id=?                              ");
		MemberVO raVO = null;
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, paVO.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				raVO = new MemberVO();
				raVO.setId(rs.getString("id"));
				raVO.setPw(rs.getString("pw"));
				raVO.setName(rs.getString("name"));
				raVO.setBirth(rs.getString("birth"));
				raVO.setGen(rs.getString("gen"));
				raVO.setAddress(rs.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return raVO;
	}

	@Override
	public MemberVO MemberInsert(MemberVO paVO) {
		MemberVO raVO = new MemberVO();
		dbMgr = DBConnectionMgr.getInstance();
		StringBuilder sql = new StringBuilder();
		int status = 0;
		try {
			sql.append("INSERT INTO DVDMEMBER									");
			sql.append("(id,pw,name,birth,gen,address,regdate,point,late)		");
			sql.append(" VALUES(?,?,?,?,?,?,TO_CHAR(sysdate,'YYYY-MM-DD'),0,0)	");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			int i = 0;
			pstmt.setString(++i, paVO.getId());
			pstmt.setString(++i, paVO.getPw());
			pstmt.setString(++i, paVO.getName());
			pstmt.setString(++i, paVO.getBirth());
			pstmt.setString(++i, paVO.getGen());
			pstmt.setString(++i, paVO.getAddress());
			status = pstmt.executeUpdate();
			raVO.setStatus(status);
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(sql.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		
		return raVO;
	}

	@Override
	public MemberVO MemberUpdate(MemberVO paVO) {
		MemberVO raVO = new MemberVO();
		dbMgr = DBConnectionMgr.getInstance();
		StringBuilder sql = new StringBuilder();
		int status = 0;
		try {
			sql.append(" update dvdmember set pw=?, name=?, birth=?, gen=?, address=? ");
			sql.append(" 		 where id = '"+paVO.getId()+"'                   	  ");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			int i = 0;
			pstmt.setString(++i, paVO.getPw());
			pstmt.setString(++i, paVO.getName());
			pstmt.setString(++i, paVO.getBirth());
			pstmt.setString(++i, paVO.getGen());
			pstmt.setString(++i, paVO.getAddress());
			status = pstmt.executeUpdate();
			raVO.setStatus(status);
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(sql.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		return raVO;
	}

	@Override
	public MemberVO MemberDelete(MemberVO paVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DVDVO DVDInsert(DVDVO paVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DVDVO DVDDelete(DVDVO paVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> getMember() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		dbMgr = DBConnectionMgr.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id,name,birth,gen,address,regdate,point,late  ");
		sql.append("   FROM dvdmember                                     ");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			MemberVO raVO = null;
			while(rs.next()) {
				raVO = new MemberVO();
				raVO.setId(rs.getString("id"));
				raVO.setName(rs.getString("name"));
				raVO.setBirth(rs.getString("birth"));
				raVO.setGen(rs.getString("gen"));
				raVO.setAddress(rs.getString("address"));
				raVO.setRegdate(rs.getString("regdate"));
				raVO.setPoint(rs.getInt("point"));
				raVO.setLate(rs.getInt("late"));
				list.add(raVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<DVDVO> getDVD() {
		// TODO Auto-generated method stub
		return null;
	}

}
