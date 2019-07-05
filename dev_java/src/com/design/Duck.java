package com.design;

public abstract class Duck {
	int leg;
	FlyBehavior flyBehavior = null;
	QuackBehavior quackBehavior = null;
	public Duck() {}
	public void prefly() {
		flyBehavior.fly();
	}
	public void prequak() {
		quackBehavior.quack();
	}
	public abstract void display();
	public void swimming() {
		System.out.println("난 수영입니다");
	}
}
