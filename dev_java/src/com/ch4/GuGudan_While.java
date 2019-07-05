package com.ch4;

public class GuGudan_While {

	public static void main(String[] args) {
		int x=1, y=2;
		while(x<10) {
			while(y<10) {
				System.out.print(x+"*"+y+"="+x*y+"	");
				y++;
			}
			x++;
			System.out.println();
		}
	}
}