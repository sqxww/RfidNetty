package com.rfid.netty.handler;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.fabric.xmlrpc.base.Params;
import com.rfid.netty.domain.Label;
import com.rfid.netty.pojo.Header;
import com.rfid.netty.pojo.MessageType;
import com.rfid.netty.pojo.NettyMessage;
import com.rfid.netty.pojo.RfidSession;
import com.rfid.netty.utils.EcUtil;
import com.rfid.netty.utils.ImgMap;
import com.rfid.netty.utils.ParamsMap;
import com.rfid.netty.utils.WantedQueue;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		//判断是否是心跳请求消息
		if(message != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()){
			System.out.println("server recevie heart : ");
			RfidSession session = EcUtil.getSessionCache().get("" + message.getHeader().getSessionID());
			String body = null;
			if(ImgMap.getInstance().contains("" + session.getUserId())){
				Set<Label> labels = ImgMap.getInstance().get("" + session.getUserId());
				body = ParamsMap.getJsonString(labels);
				System.out.println("--------" + body);
			}
			if(session.getUserId() == 6){
				System.out.println("-----admin----");
				if(!WantedQueue.getInstance().isEmpty()){
					Label[] labels = WantedQueue.getInstance().remove();
					body = "";
					for(int i = 0; i < labels.length; i++){
						body += labels[i].getLabelCode() + "," + labels[i].getWanted();
						if( i != labels.length - 1)
							body += "|";
					}
					System.out.println("------------" + body);
				}
			}
			NettyMessage resp = new NettyMessage();
			Header header = new Header();
			header.setType(MessageType.HEARTBEAT_RESP.value());
			header.setSessionID(message.getHeader().getSessionID());
			resp.setHeader(header);
			resp.setBody(body);
			//返回心跳响应消息
			ctx.writeAndFlush(resp);
		}else{
			//其他消息透传
			ctx.fireChannelRead(msg);
		}
	}
}
