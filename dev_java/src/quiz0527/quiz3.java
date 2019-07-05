package quiz0527;

public class quiz3 {
		int bootch;
		String snootch;		
		public quiz3() {  
			this("snootch");   
			System.out.print("first");  
		}	
		public quiz3(String snootch) {  
			this(420, "snootchy");  
			System.out.print("second");  
		}	
		public quiz3(int bootch, String snootch) { 
			this.bootch = bootch;  
			this.snootch = snootch; 
			System.out.print("third");
		}
		public static void main(String[] args) {
			quiz3 b = new quiz3();
			System.out.print(b.snootch +" "+ b.bootch);  
			System.out.println("");
			new quiz3("safasf");//10번을 호출
		}
}
