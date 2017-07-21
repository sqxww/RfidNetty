package com.rfid.netty.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParamsMap {
	private final static ObjectMapper om = new JsonObjectMapper();
	
	public static Map<String, String> getParamsMap(String body) throws JsonProcessingException, IOException{
		Map<String, String> map = new HashMap<String, String>();
		JsonNode root = om.readTree(body);
		Iterator<String> nodeNames = root.fieldNames();
		while(nodeNames.hasNext()){
			String nodeName = nodeNames.next();
			JsonNode node = root.get(nodeName);
			map.put(nodeName, node.asText());
		}
		return map;
	}
	
	public static String getJsonString(Object obj) throws JsonProcessingException{
		return om.writeValueAsString(obj);
	}
	
	
}
