package com.ch7;

import java.util.ArrayList;

/*
 * 사원잡합에서 사원번호가 7566인 사원에 대한 정보를 조회허요  ArrayList에 담고자 한다.
 * 베네릭 타입을 무엇을 해야할까요?
 */
public class BookArrayListTest2 {

	public static void main(String[] args) {
		String name = new String("JONES");
		Double sal = new Double(3000);
		Integer deptno = new Integer(20);
		ArrayList<String> aList1 = new ArrayList<String>();
		ArrayList<Double> aList2 = new ArrayList<Double>();
		ArrayList<Integer> aList3 = new ArrayList<Integer>();
		ArrayList<Object> aList4 = new ArrayList<Object>();
//		aList1.add(name);
//		aList1.add(sal);
//		aList1.add(deptno);
//		aList2.add(name);		
//		aList2.add(sal);		
//		aList2.add(deptno);	
		aList4.add(name);
		aList4.add(sal);
		aList4.add(deptno);
		aList4.add(1, "우리 헤어져....");
		if(aList4.isEmpty()) {//중요 .isEmpty()
			System.out.println("안에 없음");
		}
		else {
			System.out.println("안에 있음");
		}
		for(Object obj:aList4) {//중요 instanceof
			if(obj instanceof String) {
				System.out.println("String is "+obj.toString());
			}
			else if(obj instanceof Double) {
				System.out.println("Double is "+Double.valueOf(obj.toString())+10);
			}
			else if(obj instanceof Integer) {
				System.out.println("Integer is "+obj.toString());
			}
		}
		String msg = aList4.remove(1).toString();
		System.out.println("msg is "+msg);
		System.out.println("after===================");
		for(Object obj:aList4) {
			System.out.println(obj.toString());
		}
	}

}
