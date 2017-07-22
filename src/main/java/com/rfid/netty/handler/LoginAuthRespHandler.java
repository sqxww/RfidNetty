package com.rfid.netty.handler;

import java.util.Map;
import java.util.Random;

import org.ehcache.Cache;

import com.rfid.netty.action.service.UserService;
import com.rfid.netty.domain.UserInfo;
import com.rfid.netty.pojo.Header;
import com.rfid.netty.pojo.MessageType;
import com.rfid.netty.pojo.NettyMessage;
import com.rfid.netty.pojo.RfidSession;
import com.rfid.netty.pojo.StatuType;
import com.rfid.netty.utils.ApplicationContextUtil;
import com.rfid.netty.utils.EcUtil;
import com.rfid.netty.utils.ParamsMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		AttributeKey<RfidSession> key = AttributeKey.valueOf("session");
		Attribute<RfidSession> csa = ctx.channel().attr(key);
		//获取通道中的会话信息
		RfidSession channelSession = csa.get();
		//获取session缓存容器
		Cache<String, RfidSession> sessionCache = EcUtil.getSessionCache();
		//判断是否是请求消息
		if(message != null && message.getHeader().getType() == MessageType.LOGIN_REQ.value()){
			NettyMessage resp = null;			
			//判断用户是否重复登陆
			if(channelSession != null){
				System.out.println("----------------重复登陆--------------------");
				resp = buildResponse(StatuType.REP_LOG.value(), channelSession.getSessionID());
			}else{
				UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
				//获取请求头中的session,若不为空则直接以session连接
				String sessionId = message.getHeader().getSessionID() + "";
				RfidSession reqSession = sessionCache.get(sessionId);
				if(reqSession != null){
					userService.updateSession(sessionId, reqSession.getUserId());
					resp = buildResponse(StatuType.SUB_OK.value(), message.getHeader().getSessionID());
					//开启推送服务线程
					/*
					 * 
					 */
				} else{
					//获取请求体
					String body = (String) message.getBody();
					//从请求体中获取账户名和密码
					/*
					 * 
					 */
					//捕获转json时异常（若直接抛会导致连接关闭）
					if(body != null){
						Map<String, String> params = ParamsMap.getParamsMap(body);
						String userName = params.get("userName");
						String password = params.get("password");
						//查询数据库，判断用户是否存在
						UserInfo user = userService.getByNameAndPass(userName, password);
						//若用户存在，生成sessionID
						if(user != null){
							long respSessionId = generateSessionId();
							//创建session对象
							RfidSession session = new RfidSession();
							session.setSessionID(respSessionId);
							session.setUserName(userName);
							session.setUserId(user.getUserId());
							//将session对象放入缓存
							sessionCache.put("" + respSessionId, session);
							//将session与当前通道进行绑定
							csa.set(session);
							resp = buildResponse(StatuType.SUB_OK.value(), respSessionId);
							//开启推送线程
							/*
							 * 
							 */
							//获取用户之前会话并将其销毁并更新数据库状态
							String preSession = user.getSessionid();
							sessionCache.remove(preSession);
							userService.updateSession(respSessionId + "", user.getUserId());
						} else{
							//返回用户不存在
							resp = buildResponse(StatuType.NO_USER.value(), 0);
						}
					}
				}

			}
			//返回响应消息
			ctx.writeAndFlush(resp);

		}else{
			Header header = message.getHeader();
			//获取sessionId
			long sessionId = header.getSessionID();
			//根据sessionId从缓存中获取session对象
			RfidSession session = sessionCache.get("" + sessionId);
			//若通道中未有sessionId说明用户未登陆
			if(channelSession == null){
				//若缓存也无会话信息，说明之前登陆已失效
				if(session == null){
					//返回未登录信息
					NettyMessage resp = buildResponse(StatuType.NO_LOG.value(), 0);
					resp.getHeader().setType(MessageType.SERVICE_RESP.value());
					ctx.writeAndFlush(resp);
					/*
					 * 
					 */
					ctx.close();
				}else{
					//判断会话生命周期
					long exitTime = System.currentTimeMillis() - session.getCreateTime().getTime();
					//若会话生命截止，从缓存中移除会话，返回未登录信息
					if(exitTime > session.getSessionLife()){
						NettyMessage resp = buildResponse(StatuType.NO_LOG.value(), 0);
						resp.getHeader().setType(MessageType.SERVICE_RESP.value());
						ctx.writeAndFlush(resp);
					} else{
						//将session与当前通道进行绑定
						csa.set(session);
						//透传请求消息
						ctx.fireChannelRead(msg);
					}
					
				}
			} else{
				//消息头中的sessionId是否与通道中的一致
				//通道中有但缓存中没有会话信息，说明别处登陆
				if(session == null && sessionId == channelSession.getSessionID()){
					NettyMessage resp = buildResponse(StatuType.OTH_LOG.value(), 0);
					resp.getHeader().setType(MessageType.SERVICE_RESP.value());
					ctx.writeAndFlush(resp);
					/*
					 * 
					 */
					ctx.close();
				} else if(session == null && sessionId != channelSession.getSessionID()){
					//返回会话错误信息
					/*
					 * 
					 */
				}else
					ctx.fireChannelRead(msg);
			}
		}

	}

	private long generateSessionId(){
		Random r = new Random(54321);
		String si = r.nextInt(99999) + "" + System.currentTimeMillis();
		long sessionId = Long.valueOf(si);
		return sessionId;
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
		System.out.println("lonin " + cause);
		//清空登陆缓存

		//关闭ctx
		ctx.close();
		System.out.println(cause);
	}
}
