package com.ch7;

public class TwoArray {
	int a[] = new int[3];
	int a2[][] = new int [2][3];
	String names[] = {"이병헌","정우성","장동건"};
	int a3[][] = {
			      {70,80}
	             ,{90,75}
	             ,{85,60}
                 };
	//세친구의 총점을 담을 1차배열 선언하기
	int tots[] = new int[3];
	//총점을 구해서 tots배열 초기화하기 구현
	public void totsInit() {
		for(int i=0;i<tots.length;i++) {
			tots[i] = a3[i][0]+a3[i][1];
		}
		avgsInit(a3[0].length);
	}
	//평균을 구해서 avgs배열 초기화하기 구현
	double avgs[] = new double[tots.length];
	public void avgsInit(int inwon) {
		for(int i=0;i<tots.length;i++) {
			avgs[i] = tots[i]/inwon;
		}
		ranking();
		
	}
	//석차를 구해서 출력해보시오.
	public void ranking() {
		int rnks[] = {1,1,1};
		for(int i=0;i<rnks.length;i++) {
			for(int j=0;j<rnks.length;j++) {
				if(tots[i]>tots[j]) {
					rnks[j]++;
				}
			}
		}
		System.out.println("[석차출력]");
		for(int rnk:rnks) {
			System.out.println(rnk);
		}
	}
	
	public static void main(String[] args) {
		TwoArray ta = new TwoArray();
		System.out.println(ta.a[0]);//0
		System.out.println(ta.a2[0]);//[I@566776ad
		for(int i=0;i<ta.a2.length;i++) {//i<2
			for(int j=0;j<ta.a2[i].length;j++) {//j<3
				System.out.println("a2["+i+"]["+j+"]="+ta.a2[i][j]);
			}
		}
		ta.totsInit();
	}

}
