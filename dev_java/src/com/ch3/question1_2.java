package com.ch3;

public class question1_2 {

	public static void main(String[] args) {
		int i = 1;
		int j = i++;//j에 i를 대입하고 i+1한다.j=1,i=2
		System.out.println("i= "+i);
		System.out.println("j= "+j);
		if((i==j) && (++i == j)) { //i=2,j=1 && i=3,j=2
			i += j;
		}
		System.out.println("i는 "+i);
	}

}
