package com.design;

public class RubberDuck extends Duck {
	public RubberDuck() {
		quackBehavior= new Squeak();
		flyBehavior = new FlyNoWay();
	}
	@Override
	public void display() {
		System.out.println("Juct a Looser, 외톨이 센척하는 양아치");
	}

}
