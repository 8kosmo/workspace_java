package com.network2_b;

import java.util.StringTokenizer;

public class StringTokenizerTest {

	public static void main(String[] args) {
		String msg = "200|명석|경애|오늘 스터디할꺼야?";
		StringTokenizer st = new StringTokenizer(msg,"|");
		int protocol = 0;
		String tmp = st.nextToken();//200
		String me = st.nextToken();//명석
		String you = st.nextToken();//경애
		String msg1 = st.nextToken();
		System.out.println(msg);
		if(tmp!=null) {
			protocol = Integer.parseInt(tmp);
		}
		System.out.println(tmp+", "+me+", "+you+", "+msg1);
		System.out.println(protocol);
	}

}
