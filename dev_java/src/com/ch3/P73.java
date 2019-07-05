package com.ch3;

public class P73 {
	public static void main(String[] args) {
		P73 P73 = new P73();//1
		double total = P73.getTotal(85.0, 80.0, 82.5);//2
		int inwon = 3;//8
		double avg = P73.getAverage(total, inwon);//9
		System.out.println("총점은 "+total+" 입니다.");//14
		System.out.println("평균은 "+avg+" 입니다.");//15
	}
	double getTotal(double woo, double gi, double ea){//3
		double total = 0.0;//4
		total = woo+gi+ea;//5
		return total;//6
	}//7
	double getAverage(double total, int inwon){//10
	    double avg = 0.0;//11
		avg = total/inwon;//12
		return avg;//13
	}
}
