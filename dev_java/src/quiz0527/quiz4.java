package quiz0527;

public class quiz4 {
	public void testIfA() {             
		if(testIfB("True")) {      			 //12        
			System.out.println("True");         
		} else {                            
			System.out.println("Not true");     
		}                                   
	}                                   
	public Boolean testIfB(String str) {
		return Boolean.valueOf(str);         //19
	}     
	/*
	 * 디폴트생성자는 jVM에서 지원하기때문에 생략, if(testIfB("True"))은 
	 * public Boolean testIfB(String str) {return Boolean.valueOf(str);}는
	 * if(testIfB(true))로 String타입을 boolean true로 변경해주는 것이다.
	 */
	public static void main(String[] args) {
		quiz4 q4 = new quiz4();
		//q4.testIfB();
		q4.testIfA();
	}

}
