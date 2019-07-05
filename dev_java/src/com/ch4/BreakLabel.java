package com.ch4;

public class BreakLabel {

	public static void main(String[] args) {
		int i=1;
		int j=2;
		start:
		while(i<3){
			while(j<3) {
				if(i<j) {
					break;
				}
				System.out.println("inner while");
			}
			System.out.println("outer while");
			i++;
		}
		System.out.println("여기");
	}

}
