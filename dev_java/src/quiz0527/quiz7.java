package quiz0527;

public class quiz7 {
	
	static void methodA() {//finally 예제
		int []a = new int[] {1,2};
		int b=5;
		try {
			b = a[1]+b;
			System.out.println(b);
		} catch(Exception e){
			System.out.println(e.toString());
		} finally {
			System.out.println("예외가 있던 없던 무조건 실행");
		}
		System.out.println("반드시 실행되어야 할 코드가 있다.");
	}

	public static void main(String[] args) {
		methodA();
		
		Float pi = new Float(3.14f);
		try {
		 if(pi>3) {
			 System.out.print("pi is bigger than 3. ");
		 }
		 else{
			 System.out.print("pi is not bigger than 3. ");
		 }
		} catch(Exception e){
			
		}finally {
		System.out.println("Have a nice day.");
		}
	}

}
