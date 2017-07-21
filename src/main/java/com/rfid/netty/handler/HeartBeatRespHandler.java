package com.rfid.netty.handler;

import com.rfid.netty.pojo.Header;
import com.rfid.netty.pojo.MessageType;
import com.rfid.netty.pojo.NettyMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		//判断是否是心跳请求消息
		if(message != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()){
			System.out.println("server recevie heart : ");
			NettyMessage resp = new NettyMessage();
			Header header = new Header();
			header.setType(MessageType.HEARTBEAT_RESP.value());
			header.setSessionID(message.getHeader().getSessionID());
			resp.setHeader(header);
			//返回心跳响应消息
			ctx.writeAndFlush(resp);
		}else{
			//其他消息透传
			ctx.fireChannelRead(msg);
		}
	}
}
