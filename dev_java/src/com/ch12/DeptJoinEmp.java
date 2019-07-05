package com.ch12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vo.DeptVO;
import com.vo.EmpVO;
/*
 * 리턴타입이 참조형인 경우
 */
public class DeptJoinEmp {
	/************************************************
	 * 아래 코드 개선해보기
	 * 문제제기
	 * List를 사용하더라도 제네릭타입을 두개 사용할 수 없다
	 * 그래서 DeptVO에 EmpVO를 선언했다
	 * 그러나 조인갯수가 5개라면...좆된다
	 * 닷트 연산자가 많으면 잣된다.
	 * 해결방법
	 * 제네릭 타입으로 Map을 생각해본다
	 * Map은 키와 값으로 정보를 관리하니까..테이블과 상관없이
	 * 여러개의 컬럼정보를 키로 사용할 수 있다
	 * 단 VO처럼 테이블은 구분되지 않는다
	 * 득과 실을 따져볼 때 그래도 득이 많은 편인 거니까
	 * List<Map>패턴으로 사용하는 것이 효과적일 겁니다.
	 * 또 하나 단점은 VO는 타입이 정해져 있어서 ClassCastingException은 예방되는 효과가 있는데
	 * Map의 경우 타입이 정해지지 않아서 캐스팅 익셉션에 노출되어 있다. 그러나 본인이 예방하고 코딩할
	 * 수 있다면 문제가 되지 않는다.
	 ************************************************/
	List<Map<String,Object>> list = null;
	Map<String,Object> rMap = null;
	public void listPrint() {
		unitTestVer2();
		Iterator<Map<String,Object>> it = list.iterator();
		while(it.hasNext()) {
			Map<String,Object> pMap = it.next();
			System.out.println(pMap.get("empno"));
			System.out.println(pMap.get("ename"));
			System.out.println(pMap.get("dname"));
		}
	}
	public void unitTestVer2() {//개선 버전
		list = new ArrayList<>();//List<Map<String,Object>> list = new ArrayList<>();
		rMap = new HashMap<String,Object>();//Map<String,Object> rMap = new HashMap<String,Object>();
		rMap.put("empno",7934);
		rMap.put("ename","MILLER");
		rMap.put("dname","ACCOUNTING");
		list.add(rMap);
		rMap = new HashMap<String,Object>();
		rMap.put("empno",7782);
		rMap.put("ename","CLARK");
		rMap.put("dname","ACCOUNTING");
		list.add(rMap);
		rMap = new HashMap<String,Object>();
		rMap.put("empno",7839);
		rMap.put("ename","KING");
		rMap.put("dname","ACCOUNTING");
		list.add(rMap);
	}
	public void unitTest() {//기존 버전
		List<DeptVO> deptList = null;
		List<EmpVO> empList = null;
		DeptVO dvo = new DeptVO();
		EmpVO evo = new EmpVO();
		evo.setEmpno(7934);
		evo.setEname("MILLER");
		dvo.setDname("ACCOUNTING");
		dvo.setEmpVO(evo);
		System.out.print(dvo.getEmpVO().getEmpno());
		System.out.print(dvo.getEmpVO().getEname());
		System.out.println(dvo.getDname());
		dvo = new DeptVO();
		evo.setEmpno(7782);
		evo.setEname("CLARK");
		dvo.setDname("ACCOUNTING");
		dvo.setEmpVO(evo);
		System.out.print(dvo.getEmpVO().getEmpno());
		System.out.print(dvo.getEmpVO().getEname());
		System.out.println(dvo.getDname());
		dvo = new DeptVO();
		evo.setEmpno(7839);
		evo.setEname("KING");
		dvo.setDname("ACCOUNTING");
		dvo.setEmpVO(evo);
		System.out.print(dvo.getEmpVO().getEmpno());
		System.out.print(dvo.getEmpVO().getEname());
		System.out.println(dvo.getDname());
	}
	public static void main(String[] args) {
		DeptJoinEmp dje = new DeptJoinEmp();
//		dje.unitTest();
		dje.listPrint();
	}

}
