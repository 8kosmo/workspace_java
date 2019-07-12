package com.ch15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RandomGame {

	public static void main(String[] args) {
		//기반스트림(단독 읽기 혹은 쓰기) - InputStreamReader
		//보조스트림(반드시 기반스트림과 연계) - BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br2 = new BufferedReader(is);
//		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		System.out.println("0부터 9사이의 숫자를 입력하세요.");
		try {
			String user = br.readLine();
			System.out.println("사용자가 입력한 값은"+user);
			int user2 = Integer.parseInt(user);
		} catch (IOException ie) {
			// TODO: handle exception
		}
	}

}
