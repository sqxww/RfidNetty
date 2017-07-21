package com.rfid.netty.test;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.junit.Test;

import com.rfid.netty.annotation.Action;
import com.rfid.netty.main.Main;
import com.rfid.netty.pojo.ActionMethod;
import com.rfid.netty.pojo.NettyMessage;
import com.rfid.netty.utils.ActionHandlerMapping;
import com.rfid.netty.utils.ApplicationContextUtil;

public class AnnotationTest {

	@Test
	public void test() throws ClassNotFoundException {
		String packge = "com.rfid.netty.action.handler";
		String packPath = packge.replaceAll("\\.", "/");
		URL url = Thread.currentThread().getContextClassLoader().getResource(packPath);
		File files = new File(url.getPath());
		if(files != null && files.isDirectory()){
			File[] fileList = files.listFiles();
			for(File file : fileList){
				if(file.isFile()){
					String fileName = file.getName();
					if(fileName.endsWith(".class")){
						String className = fileName.substring(0, fileName.length() - 6);
						System.out.println(className);
						Class<?> clazz = Class.forName(packge + "." + className);
						System.out.println(clazz);
						Method[] methods = clazz.getMethods();
						for(Method method : methods){
							if(method.isAnnotationPresent(Action.class)){
								Action action = method.getAnnotation(Action.class);
								System.out.println(action.value());
								Class<?>[] clazzes = method.getParameterTypes();
								System.out.println(clazzes.length);
								for(Class<?> pClass : clazzes){
									System.out.println(pClass.getName());
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Test
	public void mainTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Main.init("com.rfid.netty.action.handler");
		ActionMethod actionMethod = ActionHandlerMapping.get("getuser");
		System.out.println(actionMethod.getObjId());
		NettyMessage message = new NettyMessage();
		message.setBody("hello world");
		Method method = actionMethod.getMethod();
		method.invoke(ApplicationContextUtil.getBean(actionMethod.getObjId()), message);
	}

}
