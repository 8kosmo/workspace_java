package json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonTest1 {
	public static void jsonFormat(String key3) {
		JsonParser jsonPar = new JsonParser();
		JsonArray array = jsonPar.parse(key3).getAsJsonArray();
		for(JsonElement el:array) {
			System.out.println(el);
			String temp = el.toString();
			JsonParser jsonPar1 = new JsonParser();
			JsonObject jsonObj = jsonPar1.parse(temp).getAsJsonObject();
			String value = jsonObj.get("age").toString();
			System.out.println(value);
		}
	}
	public static void main(String[] args) {
		String key = "{\"key\":\"abcd1234\"}";
		String key2 = "{\"key\":\"abcd1234\",\"name\":\"김유신\"}";
		String key3 = "[{\"key\":\"abcd1234\",\"name\":\"김유신\",\"age\":30}]";
		System.out.println(key);//{"key":"abcd1234"}
		//위 문자열은 json포맷이 아니다.
		//왜냐하면 바깥쪽에 대괄호가 없으니깐
		//그래서 Gson으로 파싱이 안됨.
		JsonParser jsonPar = new JsonParser();
		JsonObject jsonObj = jsonPar.parse(key).getAsJsonObject();
		String value = jsonObj.get("key").toString();
		System.out.println(value);//"abcd1234"
		jsonPar = new JsonParser();
		JsonObject jsonObj2 = jsonPar.parse(key2).getAsJsonObject();
		String value2 = jsonObj2.get("name").toString();
		System.out.println(value2);//"김유신"
		jsonFormat(key3);
		
		
	}

}
