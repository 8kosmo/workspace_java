package com.ch4;

import java.util.Random;

//연습문제 0부터 9사이의 임의의 숫자를 5개 채번하고 오름차순으로 정렬하시오.
public class _0516test2 {
	int is[] = new int[5];
	void RandomValue() {
		Random r = new Random();
		for(int i=0;i<5;i++) {
			is[i] = r.nextInt(10);
		}
	}
	public static void main(String[] args) {
		_0516test2 t2 = new _0516test2();
		t2.RandomValue();
	}
}
