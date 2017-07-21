package com.rfid.netty.action.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfid.netty.action.service.LabelService;
import com.rfid.netty.annotation.Action;
import com.rfid.netty.domain.Label;
import com.rfid.netty.pojo.BodyType;
import com.rfid.netty.pojo.Header;
import com.rfid.netty.pojo.MessageType;
import com.rfid.netty.pojo.NettyMessage;
import com.rfid.netty.pojo.RfidSession;
import com.rfid.netty.pojo.StatuType;
import com.rfid.netty.utils.ParamsMap;

public class LabelHandler {

	@Autowired
	LabelService labelService;

	@Autowired
	ObjectMapper om;

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

	@Action("getOwnLabels")
	public NettyMessage getOwnLabels(NettyMessage msg, RfidSession session) throws JsonProcessingException{
		System.out.println("getOwnLabels");
		int userId = session.getUserId();
		NettyMessage resp = null;
		List<Label> labels = labelService.getLabelByUserId(userId);
		//判断结果是否为空
		/*
		 * 
		 */

		String body = ParamsMap.getJsonString(labels);
		resp = buildStrResp(session.getSessionID());
		resp.getHeader().setAttachment(msg.getHeader().getAttachment());
		resp.setBody(body);


		return resp;
	}

	@Action("getLabels")
	public NettyMessage getLabels(NettyMessage msg, RfidSession session) throws JsonProcessingException, IOException{
		//判断是否是管理员
		/*
		 *
		 */
		NettyMessage resp = null;
		String body = (String) msg.getBody();
		//判断消息体是否为空
		/*
		 * 
		 */
		//捕获并处理异常
		/*
		 * 
		 */
		Map<String, String> params = ParamsMap.getParamsMap(body);
		String userName = params.get("userName");
		//判断用户名是否为空
		/*
		 * 
		 */
		List<Label> labels = labelService.getLabelByUserName(userName);
		//判断结果是否为空
		/*
		 * 
		 */
		//捕获并处理异常
		/*
		 * 
		 */
		String respBody = ParamsMap.getJsonString(labels);
		resp = buildStrResp(msg.getHeader().getSessionID());
		resp.setBody(respBody);
		return resp;
	}

	@Action("getLabel")
	public NettyMessage getLabel(NettyMessage msg, RfidSession session) throws JsonProcessingException, IOException{
		NettyMessage resp = null;
		//判断是否是管理员
		/*
		 * 
		 */
		String body = (String) msg.getBody();
		//判断消息体是否为空
		/*
		 * 
		 */
		//捕获并处理异常
		/*
		 * 
		 */
		Map<String, String> params = ParamsMap.getParamsMap(body);
		String labelCode = params.get("labelCode");
		Label label = labelService.getLabelByCode(labelCode);
		//判断结果是否为空
		/*
		 * 
		 */
		//捕获并处理异常
		/*
		 * 
		 */
		String respBody = ParamsMap.getJsonString(label);
		resp = buildStrResp(session.getSessionID());
		resp.setBody(respBody);
		return resp;
	}

	@Action("getOwnLabel")
	public NettyMessage getOwnLabel(NettyMessage msg, RfidSession session) throws JsonProcessingException, IOException{
		int userId = session.getUserId();
		NettyMessage resp = null;
		String body = (String) msg.getBody();
		Map<String, String> params = ParamsMap.getParamsMap(body);
		String labelCode = params.get("labelCode");
		Label label = labelService.getLabelByUseridAndCode(labelCode, userId);
		String respBody = ParamsMap.getJsonString(label);
		resp = buildStrResp(session.getSessionID());
		resp.setBody(respBody);
		return resp;
	}

	@Action("deleLabel")
	public NettyMessage deleLabel(NettyMessage msg, RfidSession session) throws JsonProcessingException, IOException{
		NettyMessage resp = null;
		//判断是否是管理员
		/*
		 * 
		 */
		String body = (String) msg.getBody();
		//判断消息体是否为空
		/*
		 * 
		 */
		//捕获并处理异常
		/*
		 * 
		 */
		Map<String, String> params = ParamsMap.getParamsMap(body);
		String labelCode = params.get("labelCode");
		int result = labelService.deleteLabelByCode(labelCode);
		//判断删除结果
		/*
		 * 
		 */
		resp = buildStrResp(session.getSessionID());
		return resp;
	}

	@Action("addLabel")
	public NettyMessage addLabel(NettyMessage msg, RfidSession session) throws JsonParseException, JsonMappingException, IOException{
		NettyMessage resp = null;
		String body = (String) msg.getBody();
		Label label = om.readValue(body, Label.class);
		label.setLabelId(null);
		int result = labelService.addLabel(label);
		//判断更新结果
		/*
		 * 
		 */
		resp = buildStrResp(session.getSessionID());
		return resp;
	}

	@Action("updateOwnLabel")
	public NettyMessage updateOwnLabel(NettyMessage msg, RfidSession session) throws JsonParseException, JsonMappingException, IOException{
		NettyMessage resp = null;
		String body = (String) msg.getBody();
		Label label = om.readValue(body, Label.class);
		label.setUserId(session.getUserId());
		int result = labelService.updateLabelByCodeAndUserid(label);
		//判断更新结果
		/*
		 * 
		 */
		resp = buildStrResp(session.getSessionID());
		return resp;
	}

	@Action("updateLabel")
	public NettyMessage updateLabel(NettyMessage msg, RfidSession session) throws JsonParseException, JsonMappingException, IOException{
		NettyMessage resp = null;
		String body = (String) msg.getBody();
		Label label = om.readValue(body, Label.class);
		int result = labelService.updateLabelByCode(label);
		//判断更新结果
		/*
		 * 
		 */
		resp = buildStrResp(session.getSessionID());
		return resp;
	}
}
