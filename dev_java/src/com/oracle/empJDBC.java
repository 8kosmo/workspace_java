package com.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import com.util.DBConnectionMgr;
import com.vo.DeptVO;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class empJDBC {
	Connection				con = null;
	CallableStatement 	  cstmt = null;
	OracleCallableStatement ocstmt = null;
	public DeptVO[] getMy_proc3() {//객체배열
		DeptVO[] dvos = null;
		DeptVO dvo = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		try {	
			//예외가 발생할 가능성이 있는 코드
			con = dbMgr.getConnetion();
			cstmt = con.prepareCall("{call proc_refcursor(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			ocstmt = (OracleCallableStatement)cstmt;//상위클래스 타입으로 강제형전환
			ResultSet rs = ocstmt.getCursor(1);
			//ArrayList a1 = new ArrayList();//싱글스레드 안전한 자료구조
			Vector v = new Vector();//멀티스레드 안전한 구조
			while(rs.next()) {
				dvo = new DeptVO();
				dvo.setDeptno(rs.getInt("deptno"));
				dvo.setDname(rs.getString("dname"));
				dvo.setLoc(rs.getString("loc"));
				//벡터클래스를 추가한 이유는 테이블의 로우수를 알 수 없으므로 SELECT COUNT쓰지말고
				//벡터클래스에 add할때마다 원소의 갯수가 카운트된다.
				v.add(dvo);
				//System.out.println(dvo.getDeptno()+","+dvo.getDname()+","+dvo.getLoc());
				dvo = null;
			}
			dvos = new DeptVO[v.size()];
			//파라미터로 비어있는 객체배열을 넣어주면 벡터에 담긴 값들이 복제된다.
			v.copyInto(dvos);
		} catch (Exception e) {
			System.out.println("[[Exception]]"+e.toString());
		} finally {
			//사용한 자원 반납할것. -명시적으로
			//생성된 역순으로 반납처리 할것
			try {
				if(ocstmt!=null) {
					ocstmt.close();
				}
				if(cstmt!=null) {
					cstmt.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return dvos;
	}
	public ArrayList<DeptVO> getMy_proc2() {//ArrayList
		ArrayList<DeptVO> deptList = new ArrayList<DeptVO>();
		DeptVO dvo = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		try {	
			//예외가 발생할 가능성이 있는 코드
			con = dbMgr.getConnetion();
			cstmt = con.prepareCall("{call proc_refcursor(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			ocstmt = (OracleCallableStatement)cstmt;//상위클래스 타입으로 강제형전환
			ResultSet rs = ocstmt.getCursor(1);
			while(rs.next()) {
				dvo = new DeptVO();
				dvo.setDeptno(rs.getInt("deptno"));
				dvo.setDname(rs.getString("dname"));
				dvo.setLoc(rs.getString("loc"));
				deptList.add(dvo);
				System.out.println(dvo.getDeptno()+","+dvo.getDname()+","+dvo.getLoc());
			}
		} catch (Exception e) {
			System.out.println("[[Exception]]"+e.toString());
		} finally {
			//사용한 자원 반납할것. -명시적으로
			//생성된 역순으로 반납처리 할것
			try {
				if(ocstmt!=null) {
					ocstmt.close();
				}
				if(cstmt!=null) {
					cstmt.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return deptList;
	}
	public DeptVO getMy_proc() {//초기 자료
		DeptVO dvo = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		try {	
			//예외가 발생할 가능성이 있는 코드
			con = dbMgr.getConnetion();
			cstmt = con.prepareCall("{call proc_refcursor(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			ocstmt = (OracleCallableStatement)cstmt;//상위클래스 타입으로 강제형전환
			ResultSet rs = ocstmt.getCursor(1);
			while(rs.next()) {
				dvo = new DeptVO();
				dvo.setDeptno(rs.getInt("deptno"));
				dvo.setDname(rs.getString("dname"));
				dvo.setLoc(rs.getString("loc"));
				System.out.println(dvo.getDeptno()+","+dvo.getDname()+","+dvo.getLoc());
			}
		} catch (Exception e) {
			System.out.println("[[Exception]]"+e.toString());
		} finally {
			//사용한 자원 반납할것. -명시적으로
			//생성된 역순으로 반납처리 할것
			try {
				if(ocstmt!=null) {
					ocstmt.close();
				}
				if(cstmt!=null) {
					cstmt.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return dvo;
	}
	public String getProc_deptnoUpdate() {
		String msg = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		try {
			//물리적으로 떨어져 있는 오라클 서버의 연결통로확보
			//자바에서는 오토커밋이 디폴트
			//con.setAutoCommit(false);
			con = dbMgr.getConnetion();
			cstmt = con.prepareCall("{call proc_deptnoUpdate(?,?)}");
			System.out.println("부서번호를 입력하세요.");
			Scanner scan = new Scanner(System.in);
			int u_deptno = scan.nextInt();//사용자가 입력한 사원번호 받아오기
			cstmt.setInt(1, u_deptno);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);//프로젝트에서 사용
			//오라클서버에서 처리요청
			int result = cstmt.executeUpdate();
			msg = cstmt.getString(2);
			System.out.println(msg);
			//con.commit(); 프로시저안에서 commit했기때문에 안해도됨.
		} catch (Exception e) {
			//절대로 print안에 넣지 말것.
			//stack메모리 영역에 쌓여있는 에러메시지를 모두 출력해줌.
			e.printStackTrace();
		} finally {
			//사용한 자원 반납할것. -명시적으로
			//생성된 역순으로 반납처리 할것
			try {
				if(cstmt!=null) {
					cstmt.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return msg;
	}
	public String getProc_empnoUpdate() {
		String msg = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		try {
			con = dbMgr.getConnetion();
			cstmt = con.prepareCall("{call proc_empnoUpdate(?,?)}");
			System.out.println("사원번호를 입력하세요.");
			Scanner scan = new Scanner(System.in);
			int u_empno = scan.nextInt();//사용자가 입력한 사원번호 받아오기
			cstmt.setInt(1, u_empno);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);//프로젝트에서 사용
			//오라클서버에서 처리요청
			int result = cstmt.executeUpdate();
			msg = cstmt.getString(2);
			System.out.println(msg);
			//con.commit(); 프로시저안에서 commit했기때문에 안해도됨.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return msg;
	}
	public static void main(String[] args) {
		empJDBC letstarts = new empJDBC();
		//letstarts.getProc_empnoUpdate();
		//letstarts.getProc_deptnoUpdate();
		ArrayList<DeptVO> deptList = letstarts.getMy_proc2();
		for(int j=0;j<deptList.size();j++) {
			DeptVO dvo = deptList.get(j);
			System.out.println(dvo.getDeptno()+","+dvo.getDname()+","+dvo.getLoc());
		}
		System.out.println("==========개선된for문================");
		for(DeptVO dvo:deptList) {
			System.out.println(dvo.getDeptno()+","+dvo.getDname()+","+dvo.getLoc());
		}
		System.out.println("insert here===========================");
		//insert here
		letstarts.getMy_proc3();
		
		DeptVO[] dvos = letstarts.getMy_proc3();
		for(int x=0;x<dvos.length;x++) {
			DeptVO dvo = dvos[x];
			System.out.println(dvo.getDeptno()
					     +", "+dvo.getDname()
					     +", "+dvo.getLoc());
		}
		System.out.println("개선된새끼=============");
		for(DeptVO object:dvos) {
			System.out.println(object.getDeptno()+","+object.getDname()+","+object.getLoc());
		}
	}

}
