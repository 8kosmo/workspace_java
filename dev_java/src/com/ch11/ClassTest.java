package com.ch11;

import com.ch2.Sonata;
import com.ch9.Car;

public class ClassTest {
	
	public static void main(String[] args) {
		Car myCar = new Sonata();
		Class c = myCar.getClass();
		c = Sonata.class;
		if(myCar instanceof Sonata) {
			
		}
		Sonata yourCar = new Sonata();
		try {			
			Class sc = Class.forName("com.ch2.Sonata");
			Sonata himCar = (Sonata)sc.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
