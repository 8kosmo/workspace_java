package quiz0607;
public class quiz7 {}
	class Pass {
		int x; //0->3
		public static void main(String []args) {
			int x = 5;  
			Pass p = new Pass();
			p.x = 3;
			p.doStuff(p);
//			System.out.println(" main x = "+ x);
		}
	
		private void doStuff(Pass p) {
			System.out.println(" doStuff x = "+ x++);
		}
		void doStuff(int x) {
			System.out.println(" doStuff x = "+ x++);
		}
	}
