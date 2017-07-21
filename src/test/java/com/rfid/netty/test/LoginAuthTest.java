package com.rfid.netty.test;

import java.util.Random;

import org.junit.Test;

import com.rfid.netty.domain.UserInfoExample;
import com.rfid.netty.domain.UserInfoExample.Criteria;
import com.rfid.netty.mapper.UserInfoMapper;
import com.rfid.netty.utils.ApplicationContextUtil;

public class LoginAuthTest {
	
	@Test
	public void countUserTest(){
		long l = 1234567891234567891l;
		System.out.println(System.currentTimeMillis());
	}
	
	@Test
	public void generateSessionIdTest(){
		long l = generateSessionId();
		System.out.println(l);
	}
	
	private long generateSessionId(){
		Random r = new Random(54321);
		String si = r.nextInt(99999) + "" + System.currentTimeMillis();
		long sessionId = Long.valueOf(si);
		return sessionId;
	}
	
	private int countUser(String username, String password){
		UserInfoMapper userMapper = (UserInfoMapper) ApplicationContextUtil.getBean("userInfoMapper");
		UserInfoExample userExample = new UserInfoExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUserNameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		
		return (int) userMapper.countByExample(userExample);
	}
	
}
