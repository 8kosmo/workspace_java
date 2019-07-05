package com.ch12;

import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;

public class JsonFormatPrint {

	public static void main(String[] args) {
		List<ChatVO> list = new Vector<>();
		ChatVO cVO = new ChatVO();
		cVO.setNickName("묘령");
		cVO.setAge(20);
		list.add(cVO);
		cVO = new ChatVO();
		cVO.setNickName("황혼");
		cVO.setAge(60);
		list.add(cVO);
		cVO = new ChatVO();
		cVO.setNickName("불혹");
		cVO.setAge(40);
		list.add(cVO);
		Gson g = new Gson();
		String jsonChat = g.toJson(list);
		System.out.println(jsonChat);
	}

}
