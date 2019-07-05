package com.ch4;
import java.util.Random; //java.utill.Random r = new java.utill.Random();
import java.util.Scanner;
public class random {

	public static void main(String[] args) {
		Random r = new Random();//채번한 숫자를 담을 변수 선언
		Scanner scan = new Scanner(System.in);
		System.out.println("0부터 9까지 입력하세요.");
		int nansu = r.nextInt(10);
		int cnt=5;
		for(int i=1;i<=5;i++) {
			System.out.println(cnt+"번 기회가 남았습니다.");
			int userinput = scan.nextInt();
			if(userinput == nansu) {
				System.out.println("정답입니다");
				System.exit(0);//가상머신과 연결고리를 끊음.
			}
			else if(cnt==1) {
				System.out.println("넌 바보");
				System.exit(0);
			}
			else if(userinput < nansu) {
				System.out.print("더 큰수를 입력하세요.	");
				cnt--;
			}
			else if(userinput > nansu) {
				System.out.print("더 작은수를 입력하세요.	");
				cnt--;
			}
		}
	}
	
}
