package com.rfid.netty.main;

import java.util.concurrent.TimeUnit;

import com.rfid.netty.codec.RfidMessageDecoder;
import com.rfid.netty.codec.RfidMessageEncoder;
import com.rfid.netty.handler.ActionDispatcherHandler;
import com.rfid.netty.handler.HeartBeatRespHandler;
import com.rfid.netty.handler.LoginAuthRespHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class RfidServer {

	public void bind(String host, int port) throws Exception{
		//配置服务端NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			/*
			 * 配置serverBootstrap
			 */
			//使用主从线程模式
			b.group(bossGroup, workerGroup)
			//配置通道选项
			.option(ChannelOption.SO_BACKLOG, 100)
			//通道使用NioServerSocketChannel类型通道
			.channel(NioServerSocketChannel.class)
			//父处理器（此处为日志处理器）
			.handler(new LoggingHandler(LogLevel.INFO))
			//添加各子处理器
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					//添加解码器
					p.addLast("rfidDecoder", new RfidMessageDecoder(1024*1024, 4, 4, -8));
					//添加编码器
					p.addLast("rfidEncoder", new RfidMessageEncoder());
					//添加读超时处理器
					p.addLast("rfidRTO",new ReadTimeoutHandler(50, TimeUnit.SECONDS));
					//添加登录认证处理器
					p.addLast("rfidLogin", new LoginAuthRespHandler());
					//添加心跳处理器
					p.addLast("rfidHeartBeat", new HeartBeatRespHandler());
					//添加行为转发处理器
					p.addLast("rfidDisp", new ActionDispatcherHandler());

				}
			});
			//绑定端口，同步等待成功
			ChannelFuture f =  b.bind(host, port).sync();
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} finally {
			//优雅退出,释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
