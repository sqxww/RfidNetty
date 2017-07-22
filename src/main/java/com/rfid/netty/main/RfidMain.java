package com.rfid.netty.main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.rfid.netty.annotation.Action;
import com.rfid.netty.pojo.ActionMethod;
import com.rfid.netty.utils.ActionHandlerMapping;
import com.rfid.netty.utils.ApplicationContextUtil;

public class RfidMain {
	
	public static void main(String[] args) throws IOException {
		String host = "127.0.0.1";
		int port = 8080;
		init("com.rfid.netty.action.handler");
		ApplicationContextUtil.getBean("labelService");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					new RfidServer().bind(host, port);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	
	private static void init(String packa) throws IOException{
		String packaPath = packa.replaceAll("\\.", "/");
		URL url = Thread.currentThread().getContextClassLoader().getResource(packaPath);
		String protocl = url.getProtocol();
		if(protocl.equals("jar")){
			JarURLConnection jarUrlC = (JarURLConnection) url.openConnection();
			JarFile jarFile = jarUrlC.getJarFile();
			Enumeration<JarEntry> entries = jarFile.entries();
			while(entries.hasMoreElements()){
				JarEntry entry = entries.nextElement();
				if(entry.getName().startsWith(packaPath)){
					if(!entry.isDirectory()){
						if(entry.getName().endsWith(".class")){
							String className = entry.getName().replaceAll(".*/([^/\\.]*)\\.class", "$1");
							String objId = toFirstLower(className);
							getMethods(objId);
						}
					}
				}
			}
		}else if(protocl.equals("file")){
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
