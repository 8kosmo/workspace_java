package quiz0510;

public class test5_1 {

	public static void main(String[] args) {
		Integer a = new Integer(10);
		Integer b = new Integer(10);
		Integer c = a;
		if(a==b) {
			System.out.println("주소번지가 같다");
		}
		else {
			System.out.println("변수 a의 주소번지와 b의 주소번지가 다르다");
			System.out.println("a랑 b는 값을 비교하는게 아니다");			
		}
		if(b==c) {
			System.out.println("c=a라고 했는데, b=c는 주소번지가 같아요");
		}
		else {
			System.out.println("c=a라고 했는데, b=c는 주소번지도 달라요");
		}
		if(a==c) {
			System.out.println("a=c는 주소번지수가 같죠");
		}
		else {
			System.out.println("a=c 역시 주소번지수가 달라요");
		}
	}

}
