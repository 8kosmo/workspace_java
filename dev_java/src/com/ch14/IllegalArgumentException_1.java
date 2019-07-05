package com.ch14;

public class IllegalArgumentException_1 {

	public static void main(String[] args) {
		System.out.println("0부터11까지 월 정보를 입력하세요.");
		String smm = args[0];
		int imm = Integer.parseInt(smm);
		if(imm <0 || imm>12) {
			throw new IllegalArgumentException("유효한값이아닙니다.");
		}else {
			System.out.println("정상처리");
		}
	}
}
