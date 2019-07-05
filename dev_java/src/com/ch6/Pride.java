package com.ch6;
class Sorento {
	Sorento(Pride herCar){
		herCar.speed = 150;
	}
}
public class Pride{
	int speed = 0;
	public Pride() {
		speed = 100;
		Sorento himCar = new Sorento(this); // this = Pride herCar
	}
	public static void main(String[] args) {
		Pride herCar = new Pride();
		System.out.println(herCar.speed);
	}

}