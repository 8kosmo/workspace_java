package quiz0510;

public class test5 {

	public static void main(String[] args) {
		Integer a = new Integer(10); // int타입에 대한 Wrapper클래스이다.
		Integer b = new Integer(10);
		Integer c = a;
		if(a==c) {
			System.out.println("참");
		}
		if(a==b) {
			System.out.println("참");
		}
		else {
			System.out.println("거짓");
		}
		System.out.println(a);
		int i;
		i=a;//오토박싱
		i=a.intValue();
	}

}
