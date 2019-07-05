package com.ch12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionTest1 {
/*
 * 둘다 동일한 메소드를 호출 하였고 파라미터 타입도 똑같이 0을 주었다.
 * 그러나 위에서는 파라미터가 object인 것이 호출된 것이고
 * 이 자료구조 안에는 그 값이 없으므로 처리괸 것이 없다.
 * 그러나 아래에서는 파라미터가 int인 것이 호출된 것이고 그 자리에 값이 있어
 * 값이 삭제되었다.
 * 
 * 다행인 것은 remove(Object)에어서 20번 라인이 컴파일 에러가 안난 경우, 메소드 오버로딩으로 해석
 */
	public static void main(String[] args) {
		Collection<String> coll = new ArrayList<>();
		coll.add("컬렉션");
		coll.remove(0);
		for(String array:coll) {
			System.out.println(array);
		}
		List<String> li = new ArrayList<>();
		li.add("리스트");
		li.remove(0);
		for(String array:li) {
			System.out.println(array);
		}
	}

}
