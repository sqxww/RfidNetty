package com.rfid.netty.pojo;

import java.io.Serializable;
import java.util.Date;

import org.ehcache.Cache;

import com.rfid.netty.utils.EcUtil;

public class RfidSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7535770133237411686L;
	private final long maxLife = 604800l;
	private long sessionID;
	private String userName;
	private int userId;
	private Date createTime;
	private long sessionLife;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public long getSessionLife() {
		return sessionLife;
	}
	public void setSessionLife(long sessionLife) {
		if(sessionLife > this.maxLife){
			this.sessionLife = maxLife;
			return;
		}
		this.sessionLife = sessionLife;
	}
	public long getMaxLife() {
		return maxLife;
	}
	public long getSessionID() {
		return sessionID;
	}
	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	//销毁方法
	public void distroy(){
		Cache<String, RfidSession> sessionCache = EcUtil.getSessionCache();
		sessionCache.remove("" + this.sessionID);
	}
}
