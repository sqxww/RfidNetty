package com.rfid.netty.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {
	private static final ApplicationContext context;
	
	static{
		context = new ClassPathXmlApplicationContext("classpath:conf/spring/applicationcontext-*.xml");
	}
	
	public static ApplicationContext getContext(){
		return context;
	}
	
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
}
