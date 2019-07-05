package quiz0510;

public class test5_2 {

	public static void main(String[] args) {
		String s = new String("hello");
		String s2 = new String("hello");
		if(s==s2) {
			System.out.println("String s = new String(\"hello\");");
			System.out.println("String s2 = new String(\"hello\");");
			System.out.println("s==s2는 같다");
		}
		else {
			System.out.println("String s = new String(\"hello\");");
			System.out.println("String s2 = new String(\"hello\");");
			System.out.println("s==s2는");
			System.out.println("값이 아닌 주소번지수가 다르다는 뜻이다.");
		}
		if(s.equals(s2)) {
			System.out.println("s.equals(s2)는 객체 안에 담겨있는 값이 같은지 물어보는 것이다.");
			System.out.println("s와 s2의 담겨있는 값은 같다.");
		}
		else {
			System.out.println("s.equals(s2)는 객체 안에 담겨있는 값이 같은지 물어보는 것이다.");
			System.out.println("s와 s2의 담겨있는 값은 다르다.");
		}
	}

}

