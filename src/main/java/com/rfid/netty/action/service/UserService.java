package com.rfid.netty.action.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rfid.netty.domain.UserInfo;
import com.rfid.netty.domain.UserInfoExample;
import com.rfid.netty.domain.UserInfoExample.Criteria;
import com.rfid.netty.mapper.UserInfoMapper;
import com.rfid.netty.mapper.custom.UserInfoMapperCustom;

public class UserService {
	
	@Autowired
	UserInfoMapper userMapper;
	
	@Autowired
	UserInfoMapperCustom userInfoMapperCustom;
	
	public int countUser(String userName, String password){
		UserInfoExample userExample = new UserInfoExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUserNameEqualTo(userName);
		criteria.andPasswordEqualTo(password);
		
		return (int) userMapper.countByExample(userExample);
	} 
	
	public UserInfo getByNameAndPass(String userName, String password){
		return userInfoMapperCustom.getByNameAndPass(userName, password);
	}
	
	public int updateSession(String sessionId, int userId){
		
		return userInfoMapperCustom.updateSession(sessionId, userId);
	}
	
	public List<UserInfo> getUsers(Integer pageNum,Integer pageSize){
		Integer index = (pageNum - 1) * pageSize;
		
		return userInfoMapperCustom.getUsers(index, pageSize);
	}
	
}
