package com.rfid.netty.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfid.netty.domain.Label;
import com.rfid.netty.domain.UserInfo;
import com.rfid.netty.utils.JsonObjectMapper;
import com.rfid.netty.utils.ParamsMap;

public class ParamsMapTest {

	@Test
	public void test() throws JsonProcessingException, IOException {
		String body = "{\"username\":\"tom\",\"password\":\"123456\"}";
		Map<String, String> map = ParamsMap.getParamsMap(body);
		for(Map.Entry<String, String> entry : map.entrySet()){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println(map.get("dd"));
	}
	
	@Test
	public void convertTest() throws JsonProcessingException{
		UserInfo user = new UserInfo();
		user.setPassword("1231");
		user.setAddress("sdlf");
		user.setUserName("dsgfasd");
		System.out.println(ParamsMap.getJsonString(user));
		UserInfo user1 = new UserInfo();
		user1.setAddress("dsf");
		user1.setOnline((short)1);
		user1.setUserName("dsgsdf");
		List<UserInfo> users = new ArrayList<UserInfo>();
		users.add(user);
		users.add(user1);
		System.out.println(ParamsMap.getJsonString(users));
	}
	
	@Test
	public void omTest() throws JsonParseException, JsonMappingException, IOException{
		String body = "{\"labelId\":\"2\",\"userId\":\"1\",\"labelName\":\"helloName\"}";
		ObjectMapper om = new JsonObjectMapper();
		Label label = om.readValue(body, Label.class);
		System.out.println(label);
	}

}
