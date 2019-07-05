package com.ch7;

import java.util.ArrayList;

public class BookArrayListTest {
	//맥북 3대를 담을 참조변수가 필요하다.
	public ArrayList<Book> setArrayList() {
		ArrayList macList  = new ArrayList();
		ArrayList<Book> macList2 = new ArrayList<Book>();
		Book myBook  	= new Book("11분");
		Book herBook  	= new Book("연금술사");
		Book yourBook  	= new Book("자히르");
		
		boolean isMyBook  = macList2.add(myBook);
		if(isMyBook) System.out.println
		("boolean ismyBook : "+isMyBook);
		else System.out.println("담기실패");
		
		boolean isHerBook  = macList2.add(herBook);
		if(isHerBook) System.out.println
		("Book herBook : "+herBook);
		else System.out.println("담기실패");
		
		boolean isYourBook  = macList2.add(yourBook);		
		if(isYourBook) System.out.println
		("Book yourBook : "+yourBook);
		else System.out.println("담기실패");
		return macList2;
	}
	public static void main(String[] args) {
		BookArrayListTest b1 = new BookArrayListTest();
		//메소드의 리턴 타입으로 주소번지를 받아온다.
		ArrayList<Book>macList2 = b1.setArrayList();
		//개선된 for문으로 사용하여 반복처리
		System.out.println("=======================");
		for(Book mbook:macList2) {
			//AtrrayList안에 담긴 타입이 Book타입이므로 타입을 맞춘다.
			//Book mbook = (Book)obj;			
			//System.out.println(obj);
			//주소번지를 활용하여 전역변수를 출력한다.
			System.out.println(mbook.name);
		}
		System.out.println("=====개선된새끼======");
		for(int i=0;i<macList2.size();i++) {
			Book mbook = (Book)macList2.get(i);
			String name1 = mbook.name;
			System.out.println("name1:"+name1);
			
			Book mbook2 = macList2.get(i);
			String name2 = mbook2.name;
			System.out.println("name2:"+name2);
		}
	}

}
