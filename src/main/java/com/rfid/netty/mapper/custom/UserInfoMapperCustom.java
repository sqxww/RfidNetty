package com.rfid.netty.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rfid.netty.domain.UserInfo;

public interface UserInfoMapperCustom {
	UserInfo getByNameAndPass(@Param("name") String userName, @Param("password") String password);
	
	int updateSession(@Param("sessionId") String sessionId, @Param("userId") int userId);
	
	Integer getIdByName(String userName);
	
	List<UserInfo> getUsers(@Param("index") Integer index, @Param("size") Integer size);
}
