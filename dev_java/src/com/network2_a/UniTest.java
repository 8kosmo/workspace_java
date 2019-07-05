package com.network2_a;

public class UniTest {

	public static void main(String[] args) {
		ChatDao cDao = new ChatDao();
		String nick=cDao.login("test", "123");
		System.out.println(nick);
	}
}
