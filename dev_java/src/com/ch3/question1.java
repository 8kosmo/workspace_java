package com.ch3;

public class question1 {

	public static void main(String[] args) {
		int i = 1;
		int j = i++;//j에 i를 대입하고 i+1한다.j=1,i=2
		System.out.println("i= "+i);
		System.out.println("j= "+j);
		if((i==++j)|(i++==j)) {//i는 j+1과 같다. i=2, j=2 이것이 참이라면  | i+1은 j와 같다. i=2, j=3
			i+=j;              // i에 i+j를 대입한다. i=2+3
		}
		/*
		 * |연산자가 한개든 두개이든 실행문이 실행되고 안되고는 동일하다.
		 * 다른것은 한개 일때는 두번째 조건을 무조건 따진다.
		 * 2번째 조건에서 증감연산자 있게 되면 변수 i나 j값에 영향을 미친다.
		 */
		System.out.println("i= "+i);
		System.out.println("j= "+j);
		}

}
