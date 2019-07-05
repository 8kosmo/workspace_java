package com.ch3;

public class question1_1 {

	public static void main(String[] args) {
		int i = 1;
		int j = i++;//j에 i를 대입하고 i+1한다.j=1,i=2
		System.out.println("i= "+i);
		System.out.println("j= "+j);
		if((i==++j) & (++i == j)) { //i하고 j+1이 같다면(참)i=2,j=2, i+1은 j하고 같은가(거짓)i=3,j=2 
			i+=j; // 앞에  & (++i == j))가 거짓이기 때문에 대입하지 않는다.
		}
		System.out.println("i = "+ i);
	}

}
