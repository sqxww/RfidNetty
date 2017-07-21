package com.rfid.netty.test;

import org.ehcache.Cache;
import org.junit.Test;

import com.rfid.netty.pojo.RfidSession;
import com.rfid.netty.utils.EcUtil;

public class EhcacheTest {
	
	@Test
	public void EcTest() throws InterruptedException{
		RfidSession rs = new RfidSession();
		rs.setSessionID(1244l);
		rs.setUserName("122");
		Cache<String, RfidSession> cache = EcUtil.getSessionCache();
		cache.remove("334ed");
		cache.put("" + rs.getSessionID(), rs);
		System.out.println(cache.get("" + rs.getSessionID()));
		Thread.sleep(1000);
		System.out.println(cache.get("" + rs.getSessionID()));
	}
	
}
