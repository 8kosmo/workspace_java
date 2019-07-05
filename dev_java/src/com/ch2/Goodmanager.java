package com.ch2;

public class Goodmanager {

	public static void main(String[] args) {
		OracleConnection oc = new OracleConnection();
		System.out.println(oc._IP);
		System.out.println(oc.PORT);
		System.out.println(oc.user);
		System.out.println(oc.pw);
	}

}
