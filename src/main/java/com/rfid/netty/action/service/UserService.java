package com.rfid.netty.action.service;

import java.io.FileInputStream;
import java.io.IOException;
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
	
	public List<UserInfo> getUsers(){
		
		return userInfoMapperCustom.getUsers();
	}
	
	public byte[] getIconsById(int iconId){
		String iconPath = userInfoMapperCustom.getIconsById(iconId);
		if(iconPath == null)
			return null;
		byte[] imgBytes = null;
		try {
			FileInputStream fi = new FileInputStream(iconPath);
			imgBytes = new byte[fi.available()];
			fi.read(imgBytes);
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgBytes;
	}
	
	public int updateOwnIcon(int iconId, int userId){
		String iconPath = userInfoMapperCustom.getIconsById(iconId);
		if(iconPath == null)
			return 0;
		return userInfoMapperCustom.updateOwnIcon(iconPath, userId);
	}
	
	public byte[] getIconById(int userId){
		String iconPath = userInfoMapperCustom.getIconById(userId);
		if(iconPath == null)
			return null;
		byte[] imgBytes = null;
		try {
			FileInputStream fi = new FileInputStream(iconPath);
			imgBytes = new byte[fi.available()];
			fi.read(imgBytes);
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgBytes;
	}
	
}
