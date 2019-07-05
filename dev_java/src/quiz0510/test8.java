package quiz0510;

public class test8 {
	static int j = 0;
	public static boolean methodB(int k) {
		j=j+k;
		return true;
	}
	public static void methodA(int i) {//파라미터 자리에 i 지역변수
		boolean b;// 지역변수:초기화
		System.out.println(i);
		b=false;
		System.out.println(b);
	}
	public static void main(String[] args) {
		
	}

}
