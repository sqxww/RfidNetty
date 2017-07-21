package com.rfid.netty.action.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rfid.netty.action.service.UserService;
import com.rfid.netty.annotation.Action;
import com.rfid.netty.domain.UserInfo;
import com.rfid.netty.pojo.BodyType;
import com.rfid.netty.pojo.Header;
import com.rfid.netty.pojo.MessageType;
import com.rfid.netty.pojo.NettyMessage;
import com.rfid.netty.pojo.RfidSession;
import com.rfid.netty.pojo.StatuType;
import com.rfid.netty.utils.ParamsMap;

public class UserHandler {
	
	@Autowired
	UserService userService;
	
	@Action("getuser")
	public void test(NettyMessage message, RfidSession session){
		System.out.println(message.getBody());
	}
	
	private NettyMessage buildStrResp(long sessionId){
		NettyMessage resp = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.SERVICE_RESP.value());
		header.setBodyType(BodyType.STR.value());
		header.setStatu(StatuType.SUB_OK.value());
		header.setSessionID(sessionId);
		resp.setHeader(header);
		return resp;
	}
	
	@Action("getUsers")
	public NettyMessage getUsers(NettyMessage message, RfidSession session){
		
		NettyMessage resp = null;
		Map<String, String> params = null;
		try {
			params = ParamsMap.getParamsMap((String)message.getBody());
			Integer pageNum = Integer.parseInt(params.get("pageNum"));
			Integer pageSize = Integer.parseInt(params.get("pageSize"));
			List<UserInfo> users = userService.getUsers(pageNum, pageSize);
			String respBody = ParamsMap.getJsonString(users);
			resp = buildStrResp(message.getHeader().getSessionID());
			resp.setBody(respBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resp;
	}
	
}
