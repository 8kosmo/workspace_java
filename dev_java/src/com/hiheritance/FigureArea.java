package com.hiheritance;

public class FigureArea {
	//밑변 * 높이 * 1/2
	public double area(int width, int height, double d) {
		double t_area = 0.0;
		t_area = width*height*d;
		return t_area;
	}
	public double area(int width, int height) {
		double r_area = 0.0;
		r_area = width*height;
		return r_area;
	}
	public double area(double r, double pi) {
		double c_area = 0.0;
		c_area = r*r*pi;
		return c_area;
	}
}
