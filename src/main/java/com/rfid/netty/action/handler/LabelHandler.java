package com.rfid.netty.action.handler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.rfid.netty.domain.RfidSys;
import com.rfid.netty.pojo.BodyType;
import com.rfid.netty.pojo.Header;
import com.rfid.netty.pojo.MessageType;
import com.rfid.netty.pojo.NettyMessage;
import com.rfid.netty.pojo.RfidSession;
import com.rfid.netty.pojo.StatuType;
import com.rfid.netty.utils.ImgMap;
import com.rfid.netty.utils.ParamsMap;
import com.rfid.netty.utils.WantedQueue;

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

	//刷卡请求
	@Action("readLabel")
	public NettyMessage readLabel(NettyMessage msg, RfidSession session){
		System.out.println("----------readLabel--------");
		Map<String, String> params = null;
		try {
			params = ParamsMap.getParamsMap((String)msg.getBody());
			//		NettyMessage resp = null;
			//标签码
			String labelCode = params.get("labelCode");
			//坐标
			String x = params.get("x");
			String y = params.get("y");
			//时间
			String lastTime = params.get("lastTime");
			//地点
			String lastLocal = params.get("lastLocal");
			//是否是通缉到
			String found = params.get("found");
			//图片
			//		byte[] img = (byte[]) msg.getBody();
			//		if(img != null && img.length > 0){
			//			FileOutputStream fo = null;
			//			try {
			//				String photoPath = "C://rfid//photo//" + new Date().getTime() + ".jpg";
			//				fo = new FileOutputStream(photoPath);
			//				fo.write(img);
			//				fo.flush();
			Label label = new Label();
			label.setLabelCode(labelCode);
			label.setLastLocal(lastLocal);
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			label.setLastTime(df.parse(lastTime));
			label.setX(Double.parseDouble(x));
			label.setY(Double.parseDouble(y));
			label.setFound(Short.parseShort(found));

			labelService.updateLabelByCode(label);
			String userId = labelService.getUserId(labelCode) + "";
			ImgMap.getInstance().put(userId, label);
			//			} catch (IOException e) {
			//				e.printStackTrace();
			//			}finally{
			//				if(fo != null)
			//					try {
			//						fo.close();
			//					} catch (IOException e) {
			//						e.printStackTrace();
			//					}
			//			}

			//		}
			//		resp = buildStrResp(session.getSessionID());
			//		resp.getHeader().setAttachment(msg.getHeader().getAttachment());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Action("labelimg")
	public NettyMessage labelImg(NettyMessage msg, RfidSession session){
		byte[] img = (byte[]) msg.getBody();
		if(img != null && img.length > 0){
			FileOutputStream fo = null;
			String photoPath = "C://rfid//photo//" + new Date().getTime() + ".jpg";
			try {
				fo = new FileOutputStream(photoPath);
				fo.write(img);
				fo.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String labelCodes = msg.getHeader().getAttachment().get("labelcode");
			System.out.println("-----------" + labelCodes);
			String[] labels = null;
			if(labelCodes != null)
				labels = labelCodes.split(",");
			if(labels != null)
				labelService.updateLabelImg(photoPath, labels);
		}
		return null;
	}

	//通缉请求
	@Action("wantLabel")
	public NettyMessage wantLabel(NettyMessage msg, RfidSession session){
		System.out.println("----------wantLabel-----------");
		String body = (String) msg.getBody();
		NettyMessage resp = null;
		Map<String, String> params = null;
		try {
			params = ParamsMap.getParamsMap(body);
			String labelCode = params.get("labelCode");
			String wanted = params.get("wanted");
			Label label = new Label();
			label.setLabelCode(labelCode);
			label.setWanted(Short.parseShort(wanted));
			labelService.updateLabelByCode(label);
			WantedQueue.getInstance().put(label);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp = buildStrResp(session.getSessionID());
		resp.getHeader().setAttachment(msg.getHeader().getAttachment());
		return resp;
	}

	//通缉到请求
	@Action("findLabel")
	public NettyMessage findLabel(NettyMessage msg, RfidSession session){
		Map<String, String> attch = msg.getHeader().getAttachment();
		//标签码
		String labelCode = attch.get("labelCode");
		//坐标
		String x = attch.get("x");
		String y = attch.get("y");
		//时间
		String lastTime = attch.get("lastTime");
		//地点
		String lastLocal = attch.get("lastLocal");
		//图片
		byte[] img = (byte[]) msg.getBody();
		if(img != null && img.length > 0){
			FileOutputStream fo = null;
			try {
				String photoPath = "C://rfid//photo//" + new Date().getTime() + ".jpg";
				fo = new FileOutputStream(photoPath);
				fo.write(img);
				fo.flush();
				Label label = new Label();
				label.setLabelCode(labelCode);
				label.setLastLocal(lastLocal);
				label.setLastTime(new Date(Long.parseLong(lastTime)));
				label.setX(Double.parseDouble(x));
				label.setY(Double.parseDouble(y));

				labelService.updateLabelByCode(label);
				ImgMap.getInstance().put("userid", label);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(fo != null)
					try {
						fo.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

		}
		return null;
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

	@Action("getRfidSys")
	public NettyMessage getRfidSys(NettyMessage msg, RfidSession session) throws JsonProcessingException{
		System.out.println("------------getRfidSys-------------");
		NettyMessage resp = null;
		RfidSys rfidSys = labelService.getRfidSys();
		//判断结果是否为空
		/*
		 * 
		 */

		String body = ParamsMap.getJsonString(rfidSys);
		resp = buildStrResp(session.getSessionID());
		resp.getHeader().setAttachment(msg.getHeader().getAttachment());
		resp.setBody(body);


		return resp;
	}

	@Action("getOwnLabelImg")
	public NettyMessage getOwnLabelImg(NettyMessage msg, RfidSession session){
		String req = (String) msg.getBody();
		Label label = null;
		try {
			label = om.readValue(req, Label.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setUserId(session.getUserId());
		NettyMessage resp = buildStrResp(session.getSessionID());
		resp.getHeader().setBodyType(BodyType.JPG.value());
		byte[] body = labelService.getImgByCodeAndUserid(label);
		resp.setBody(body);
		return resp;
	}

	@Action("getLabelImg")
	public NettyMessage getLabelImg(NettyMessage msg, RfidSession session){

		return null;
	}

	@Action("getOwnLabelIcon")
	public NettyMessage getOwnLabelIcon(NettyMessage msg, RfidSession session) throws JsonParseException, JsonMappingException, IOException{
		String req = (String) msg.getBody();
		Label label = om.readValue(req, Label.class);
		label.setUserId(session.getUserId());
		NettyMessage resp = buildStrResp(session.getSessionID());
		resp.getHeader().setBodyType(BodyType.JPG.value());
		byte[] body = labelService.getIconByCodeAndUserid(label);
		resp.setBody(body);
		return resp;
	}

	@Action("getLabelIcon")
	public NettyMessage getLabelIcon(NettyMessage msg, RfidSession session){

		return null;
	}

	@Action("getLabels")
	public NettyMessage getLabels(NettyMessage msg, RfidSession session) throws JsonProcessingException, IOException{
		System.out.println("-------------getLabels--------------");
		//判断是否是管理员
		/*
		 *
		 */
		NettyMessage resp = null;
		//判断用户名是否为空
		/*
		 * 
		 */
		List<Label> labels = labelService.getLabels();
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
		//判断是否是管理员
		/*
		 * 
		 */
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

	@Action("getLabelIcons")
	public NettyMessage getLabelIcons(NettyMessage msg, RfidSession session){
		NettyMessage resp = null;
		String body = (String) msg.getBody();
		try {
			Map<String, String> params = ParamsMap.getParamsMap(body);
			String param = params.get("iconId");
			int iconId = Integer.parseInt(param);
			resp = buildStrResp(session.getSessionID());
			resp.getHeader().setBodyType(BodyType.JPG.value());
			byte[] respBody = labelService.getIconsById(iconId);
			resp.setBody(respBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

	@Action("updateOwnLabelIcon")
	public NettyMessage updateOwnLabelIcon(NettyMessage msg, RfidSession session){
		NettyMessage resp = null;
		String body = (String) msg.getBody();
		try {
			Map<String, String> params = ParamsMap.getParamsMap(body);
			String iconParam = params.get("iconId");
			String labelCode = params.get("labelCode");
			int userId = session.getUserId();
			int iconId = Integer.parseInt(iconParam);
			int result = labelService.upadateOwnLabelIcon(iconId, labelCode, userId);


			resp = buildStrResp(session.getSessionID());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}
}
