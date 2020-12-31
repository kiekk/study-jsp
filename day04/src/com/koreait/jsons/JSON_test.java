package com.koreait.jsons;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON_test {
	public String json;
	
	public void jsonAdd() {
		//데이터를 담기 위해서 JSONObject 타입의 객체를 생성합니다.
		JSONObject jObj_in = new JSONObject();
		JSONObject jObj_out = new JSONObject();
		//내부 요소 작성
		jObj_in.put("name", "한동석");
		jObj_in.put("gender", "남자");
		jObj_in.put("nation", "Republic of Korea");
		//외부 요소 작성
		jObj_out.put("jObj_in", jObj_in);
		//전체 내용을 문자열 값으로 변경 후 전역변수 JSON에 담아줍니다.
		json = jObj_out.toJSONString();
		//내용 확인
		System.out.println(json);
	}
	
	public static void main(String[] args) {
		//new JSON_test().jsonAdd();
		JSON_test j_test = new JSON_test();
		JSONParser p = new JSONParser();
		j_test.jsonAdd();
		
		JSONObject json_obj = null;
		JSONObject result_obj = null;
		
		String name = null;
		String gender = null;
		String nation = null;
		
		try {
			//전체 내용(문자열, j_test.json)을 JSONObject타입으로 형변환(파싱)
			json_obj = (JSONObject)p.parse(j_test.json);
			//외부요소로부터 내부요소를 가지고 옵니다.(get(K));
			result_obj = (JSONObject)json_obj.get("jObj_in");
			//내부요소로부터 값을 가지고 옵니다.
			//JSONObject니까 String으로 다운 캐스팅 합니다.
			name = (String)result_obj.get("name");
			gender = (String)result_obj.get("gender");
			nation = (String)result_obj.get("nation");
			
			System.out.println("이름 : " + name);
			System.out.println("성별 : " + gender);
			System.out.println("국가 : " + nation);
			
		} catch (ParseException e) {
			System.out.println(e);
			System.out.println("json_obj 파싱 오류");
		}
	}
}
