package com.rfid.netty.utils;

import java.net.URL;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import com.rfid.netty.pojo.RfidSession;

public class EcUtil {
	private final static CacheManager manager;
	static{
		final URL myUrl = EcUtil.class.getResource("/conf/ehcache.xml"); 
		XmlConfiguration xmlConfig = new XmlConfiguration(myUrl); 
		manager = CacheManagerBuilder.newCacheManager(xmlConfig); 
		manager.init();
	}
	
	public static CacheManager getManager(){
		return manager;
	}
	
	public static Cache<String, RfidSession> getSessionCache(){
		return manager.getCache("session", String.class, RfidSession.class);
	}
}
