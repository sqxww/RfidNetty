package com.rfid.netty.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rfid.netty.action.service.UserService;
import com.rfid.netty.domain.UserInfo;
import com.rfid.netty.utils.ApplicationContextUtil;

public class UserServiceTest {

	@Test
	public void test() {
		UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
		userService.countUser("sqxww", "123456");
	}
	
	@Test
	public void getByNameAndPassTest(){
		UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
		UserInfo user = userService.getByNameAndPass("sqxww", "123456");
		System.out.println(user.getLastLogin());
	}
	
	@Test
	public void updateSessionTest(){
		UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
		userService.updateSession("123", 1);
	}

}
