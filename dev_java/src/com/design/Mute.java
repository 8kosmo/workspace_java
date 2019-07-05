package com.design;

public class Mute implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Would you Shut up?");
	}

}
