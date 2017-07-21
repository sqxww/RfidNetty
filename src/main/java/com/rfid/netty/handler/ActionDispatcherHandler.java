package com.rfid.netty.handler;

import org.ehcache.Cache;

import com.rfid.netty.pojo.ActionMethod;
import com.rfid.netty.pojo.Header;
import com.rfid.netty.pojo.MessageType;
import com.rfid.netty.pojo.NettyMessage;
import com.rfid.netty.pojo.RfidSession;
import com.rfid.netty.pojo.StatuType;
import com.rfid.netty.utils.ActionHandlerMapping;
import com.rfid.netty.utils.ApplicationContextUtil;
import com.rfid.netty.utils.EcUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ActionDispatcherHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		Header header = message.getHeader();
		Cache<String, RfidSession> sessionCache = EcUtil.getSessionCache();
		//判断是否是service请求
		if(header.getType() == MessageType.SERVICE_REQ.value()){
			//从请求头中获取sessionId
			long sessionId = header.getSessionID();
			//根据sessionId获取会话信息
			RfidSession session = sessionCache.get("" + sessionId);
			if(session == null){
				//会话不存在，返回未登录信息
				NettyMessage resp = buildResponse(StatuType.NO_LOG.value(), 0);
				ctx.writeAndFlush(resp);
			}else{
				//获取action
				String action = header.getAttachment().get("action");
				//获取action对应的actionMethod
				ActionMethod actionMethod = ActionHandlerMapping.get(action);
				if(actionMethod == null){
					//返回action不存在错误消息
					NettyMessage resp = buildResponse(StatuType.NO_ACT.value(), sessionId);
					ctx.writeAndFlush(resp);
				}else{
					//执行actionMethod
					NettyMessage respMsg = (NettyMessage) actionMethod.getMethod().invoke(ApplicationContextUtil.getBean(actionMethod.getObjId()), message, session);
					//返回响应信息
					ctx.writeAndFlush(respMsg);
				}
				
			}
			
		}else{
			//返回错误请求响应
			NettyMessage resp = buildResponse(StatuType.TYP_ERR.value(), header.getSessionID());
			ctx.writeAndFlush(resp);
		}
	}
	
	private NettyMessage buildResponse(byte statu, long sessionId){
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_RESP.value());
		header.setSessionID(sessionId);
		header.setStatu(statu);
		message.setHeader(header);
		return message;
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}
}
