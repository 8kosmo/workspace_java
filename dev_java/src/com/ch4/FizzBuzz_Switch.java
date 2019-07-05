package com.ch4;

public class FizzBuzz_Switch {

	public static void main(String[] args) {
		for(int i=1;i<=35;i++) {
			switch(i%35) {
			case 0:
				System.out.println("FizzBuzz");
			    break;
			case 5: case 10: case 15: case 20: case 25: case 30:
				System.out.println("Fizz");
				break;
			case 7: case 14: case 21: case 28:
				System.out.println("Buzz");
				break;
			default:
				System.out.print(i+" ");
		    }
	    }
    }
}

