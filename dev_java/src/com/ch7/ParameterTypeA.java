package com.ch7;

public class ParameterTypeA {
	void methodA(int jumsu[]) {
		//insert here 
		for(int i=0;i<jumsu.length;i++) {
			System.out.println(jumsu[i]);
		}
		for(int i:jumsu) {//배열의 인덱스를 사용할 수 없다.-구구단, 야구숫자게임
			System.out.println(i);
		}
	}
	void methodB(String names[]) {
		for(int i=0;i<names.length;i++) {
			System.out.println(names[i]);
		}
		for(String name:names) {
			System.out.println(name);
		}
	}
	void methodZ(int jumsu[],String names[]) {
		for(int i=0;i<jumsu.length;i++) {
			//insert here
			System.out.println(jumsu[i]+":"+names[i]);
		}
		for(int i:jumsu) {
			System.out.println(i+":");
		}
	}
	public static void main(String[] args) {
		//insert here - 배열선언 및 초기화 처리
		//String names[] = new String[4];//null,null,null,null
		String names2[] = new String[] {"김정은","현태호","김리아","최종현"};
		String names3[] = {"김정은","현태호","김리아","최종현"};
		int jumsu[] = null;
		jumsu = new int[4];
		jumsu[0] = 70;
		jumsu[1] = 69;
		jumsu[2] = 68;
		jumsu[3] = 67;
		ParameterTypeA pta = new ParameterTypeA();
		pta.methodA(jumsu);
		pta.methodB(names2);
		pta.methodZ(jumsu,names3);
	}

}
