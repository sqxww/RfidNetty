package com.rfid.netty.test;

import org.junit.Test;

import com.rfid.netty.action.handler.UserHandler;
import com.rfid.netty.utils.ApplicationContextUtil;

public class ContextTest {

	@Test
	public void test() {
		UserHandler userHandler = (UserHandler) ApplicationContextUtil.getBean("userHandler");
		System.out.println(userHandler);
	}

}
