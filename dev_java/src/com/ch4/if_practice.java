package com.ch4;

public class if_practice {

	public static void main(String[] args) {
		int i=1;
		int j=++i;
		System.out.println("i:"+i);//2
		System.out.println("j:"+j);//2
		    if((i==j) && (++i==++j)) {
		    //  거짓             && 참
			i += j;
			}		
	
		System.out.println("i:"+i);
		System.out.println("j:"+j);
	}

}
