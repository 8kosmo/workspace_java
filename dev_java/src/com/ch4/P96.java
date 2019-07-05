package com.ch4;

import java.util.Scanner;

public class P96 {

	public static void main(String[] args) {
		System.out.println("당신의 자바점수를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		int jumsu = scan.nextInt();
		System.out.println("당신의 입력한 점수는 "+ jumsu +" 입니다.");
		char hakjum = 'Z';
		if((jumsu/10)==10||(jumsu>100)) {
			System.out.println("잘못 입력하였습니다.");
		}else if((jumsu/10)==9||(jumsu/10)==10) {
			hakjum = 'A';
			System.out.println("당신의 학점은 "+ hakjum +" 입니다.");
		}else if((jumsu/10)==8||(jumsu/10)==10) {
			hakjum = 'B';
			System.out.println("당신의 학점은 "+ hakjum +" 입니다.");
		}else if((jumsu/10)==7||(jumsu/10)==10) {
			hakjum = 'C';
			System.out.println("당신의 학점은 "+ hakjum +" 입니다.");
		}else if((jumsu/10)==6||(jumsu/10)==10) {
			hakjum = 'D';
			System.out.println("당신의 학점은 "+ hakjum +" 입니다.");
		}else {
			hakjum = 'F';	
			System.out.println("당신의 학점은 "+ hakjum +" 입니다.");
		}
	}

}
