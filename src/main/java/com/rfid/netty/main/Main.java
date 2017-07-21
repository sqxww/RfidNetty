package com.rfid.netty.main;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

import com.rfid.netty.annotation.Action;
import com.rfid.netty.pojo.ActionMethod;
import com.rfid.netty.utils.ActionHandlerMapping;
import com.rfid.netty.utils.ApplicationContextUtil;

public class Main {
	
	public static void main(String[] args) throws Exception {
		String host = "127.0.0.1";
		int port = 8080;
		init("com.rfid.netty.action.handler");
		new RfidServer().bind(host, port);
	}
	
	public static void init(String packa){
		String packaPath = packa.replaceAll("\\.", "/");
		URL url = Thread.currentThread().getContextClassLoader().getResource(packaPath);
		File packaFile = new File(url.getPath());
		if(packaFile != null && packaFile.isDirectory()){
			File[] fileList = packaFile.listFiles();
			for(File file : fileList){
				if(file.isFile()){
					String fileName = file.getName();
					if(fileName.endsWith(".class")){
						String className = fileName.substring(0,fileName.length() - 6);
						String objId = toFirstLower(className);
						getMethods(objId);
					}
				}
			}
		}
	}
	
	private static void getMethods(String objId){
		Class<?> objClass = ApplicationContextUtil.getBean(objId).getClass();
		Method[] methods = objClass.getMethods();
		for(Method method : methods){
			if(method.isAnnotationPresent(Action.class)){
				Action action = method.getAnnotation(Action.class);
				String actionUrl = action.value();
				if(!ActionHandlerMapping.containsKey(actionUrl)){
					ActionMethod actionMethod = new ActionMethod();
					actionMethod.setMethod(method);
					actionMethod.setObjId(objId);
					ActionHandlerMapping.put(actionUrl, actionMethod);
				}
			}
		}
	}
	
	private static String toFirstLower(String str){
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
}
