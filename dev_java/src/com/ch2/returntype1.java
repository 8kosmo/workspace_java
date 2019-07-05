package com.ch2;

public class returntype1 {
	int hap(int jumsu1, int jumsu2, int jumsu3) {
		int tot = 0;
		tot = jumsu1+jumsu2+jumsu3;
		return tot;
	}
	void average(int tot, int inwon) {
		double avg = 0.0;
		avg = tot/(double)inwon; //캐스팅연산자 double
		System.out.println("평균은 "+avg+"입니다.");
	}
	public static void main(String[] args) {
		returntype1 rt = new returntype1();
		int tot = rt.hap(70, 80, 90);
		rt.average(tot, 3);
	}
}
//실행순서 14-15-16-4-5-6-7-17-9-10-11-12-18-19