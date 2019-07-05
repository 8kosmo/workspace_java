package com.ch12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/*
 * deptMap은 지역변수이다.
 * 지역변수를 다른 메소드에서 사용하려면 어떡하지?
 * 1)파라미터
 * 2)리턴타입
 */
public class MapTest {
	public void setMap() {
		//Map이라는 자료구조(백팩)는
		//ORM 솔루션
		List<Map<String,Object>> deptList = new ArrayList<>();
		Map<String,Object> deptMap = new HashMap<>();
		Map<String,Object> empMap = new HashMap<>();
		mapPrint(deptMap);
		deptMap.put("deptno", 10);
		deptMap.put("dname", "ACCOUNTING");
		deptMap.put("loc", "New York");
		deptList.add(deptMap);
		deptMap = new HashMap<>();
		deptMap.put("deptno", 20);
		deptMap.put("dname", "RESEARCH");
		deptMap.put("loc", "DALLAS");
		deptList.add(deptMap);
		deptMap = new HashMap<>();
		deptMap.put("deptno", 30);
		deptMap.put("dname", "SALES");
		deptMap.put("loc", "CHICAGO");
		deptList.add(deptMap);
		deptMap = new HashMap<>();
		deptMap.put("deptno", 40);
		deptMap.put("dname", "OPERATIONS");
		deptMap.put("loc", "BOSTON");
		deptList.add(deptMap);
		
		mapPrint(deptList);
	}
	private void mapPrint(List<Map<String, Object>> deptList) {
//		System.out.println(deptList);
		for(int i=0;i<deptList.size();i++) {
//			System.out.println(deptList.get(i));
			Map<String,Object> rMap = deptList.get(i);
			Object keys[] = rMap.keySet().toArray();//Set<String> set = deptMap.keySet(); Object keys[] = set.toArray();
			for(int j=0;j<keys.length;j++) {
				System.out.print(rMap.get(keys[j]));
				if(j==keys.length) {
					break;
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public void mapPrint(Map<String,Object>deptMap) {
		Set<String> set = deptMap.keySet();
		Object keys[] = set.toArray();
		for(int i=0;i<keys.length;i++) {
			System.out.println(keys[i]);
			System.out.println(deptMap.get(keys[i]));
		}
		/*
		 * 정리하기
		 * list도 꺼낼때는 get메소드를 호출하지만 파라미터 int이고
		 * map은 파라미터가 Object이다.
		 * list에 담을 때는 add, map에 담을 때는 put
		 * list에서 꺼낼때는 get(int), map에서 꺼낼 때는 get(Object)
		 */
	}
	public static void main(String[] args) {
		MapTest mt = new MapTest();
		mt.setMap();
		//도전 - 만일 키 종류가 100가지라면 어떡하지?
	}

}
