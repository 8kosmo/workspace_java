package com.ch2;

public class friday {
	public static void add3(Integer i) {
		int val = i.intValue();
		val += 4;
		i = new Integer(val);
		}
		
		public static void main(String args[]) {
		Integer i = new Integer(0);
		add3(i);
		System.out.println(i.intValue());
		}

	/*public static void main(String[] args) {
	    String s = new String("Hello");
	    modify(s);
	    System.out.println(s);
	    }
    public static void modify(String s) {
	    s += "world!";
	    }

	/*private static int j = 0;
	public static boolean methodB(int k) {
		j += k;
		return true;
	}
	public static void methodA(int i) {
		boolean b;
		b = i < 10 | methodB(4);
		b = i < 10 || methodB(8);
	}	
	*/
	/*
	 * private static int a;
	 */
	/*public static void modify(int a) {
		a++;
	}
	public static void main(String[] args) {
		modify(a);
		System.out.println(a);
		/*
		//methodA(0);
		//System.out.println(j);
		//byte b = 127;
		//byte c = 126;
		//int d = b - c;
		/*Integer a = new Integer(10);
		Integer b = new Integer(10);
		Integer c = a;
		int d = 10;
		double e = 10.0;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		*/
		//String s = "Hello" + 9 + 1;
		//System.out.println(s);
		//String s = 1 + 9 + "Hello";
		//System.out.println(s);
		/*boolean a = false;
		boolean b = false;
		boolean c = ((a = true) | (b = true));
		System.out.println(a + "," + b + "," + c);
		*/
//	}
	//private static void modify(int a2) {
	//	
	//}
}
