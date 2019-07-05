package com.design;

public class DuckSimulation {

	public static void main(String[] args) {
		Duck myDuck = new MallardDuck();
		System.out.println("MallardDuck");
		myDuck.prefly();
		myDuck.prequak();
		Duck herDuck = new RubberDuck();
		System.out.println("RubberDuck");
		herDuck.prefly();
		herDuck.prequak();
	}

}
