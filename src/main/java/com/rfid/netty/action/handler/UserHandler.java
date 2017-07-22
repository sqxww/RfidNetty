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

	@Action("getuser")
	public void test(NettyMessage message, RfidSession session){
		System.out.println(message.getBody());
	}

	@Action("getUsers")
	public NettyMessage getUsers(NettyMessage message, RfidSession session){
		NettyMessage resp = null;
		List<UserInfo> users = userService.getUsers();
		String respBody = null;
		try {
			respBody = ParamsMap.getJsonString(users);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		resp = buildStrResp(message.getHeader().getSessionID());
		resp.setBody(respBody);

		return resp;
	}

	@Action("getOwnIcon")
	public NettyMessage getOwnIcon(NettyMessage message, RfidSession session){
		NettyMessage resp = null;
		int userId = session.getUserId();
		byte[] body = userService.getIconById(userId);
		resp = buildStrResp(session.getSessionID());
		resp.getHeader().setBodyType(BodyType.JPG.value());
		resp.setBody(body);
		return resp;
	}

	@Action("updateOwnIcon")
	public NettyMessage updateOwnIcon(NettyMessage message, RfidSession session){
		NettyMessage resp = null;
		String body = (String) message.getBody();
		try {
			Map<String, String> params = ParamsMap.getParamsMap(body);
			String param = params.get("iconId");
			int iconId = Integer.parseInt(param);
			int userId = session.getUserId();
			int result = userService.updateOwnIcon(iconId, userId);
			resp = buildStrResp(session.getSessionID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}

	@Action("getUserIcons")
	public NettyMessage getUserIcons(NettyMessage message, RfidSession session){
		NettyMessage resp = null;
		String body = (String) message.getBody();
		try {
			Map<String, String> params = ParamsMap.getParamsMap(body);
			String param = params.get("iconId");
			int iconId = Integer.parseInt(param);
			byte[] respBody = userService.getIconsById(iconId);
			resp = buildStrResp(session.getSessionID());
			resp.getHeader().setBodyType(BodyType.JPG.value());
			resp.setBody(respBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

	@Action("delUser")
	public NettyMessage delUser(NettyMessage message, RfidSession session){

		return null;
	}

	@Action("upadateOwnUser")
	public NettyMessage upadateOwnUser(NettyMessage message, RfidSession session){

		return null;
	}

}
