package com.rfid.netty.utils;

import java.util.HashMap;
import java.util.Map;

import com.rfid.netty.pojo.ActionMethod;

public class ActionHandlerMapping {
	private static final Map<String, ActionMethod> actionHandlerMap = new HashMap<String, ActionMethod>();
	
	public static void put(String action, ActionMethod actionMethod){
		actionHandlerMap.put(action, actionMethod);
	}
	
	public static ActionMethod get(String action){
		return actionHandlerMap.get(action);
	}
	
	public static boolean containsKey(String action){
		return actionHandlerMap.containsKey(action);
	}
}
