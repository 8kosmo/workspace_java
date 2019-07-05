package com.ch4;

public class GuGudan {

	public static void main(String[] args) {
		int x, y, z;
		for(y=1;y<=9;y++) {
			for(x=2;x<=9;x++) {
				z = x * y;
				System.out.print(x+"*"+y+"="+z+"	");
			}
			System.out.println();
		}
	}
}