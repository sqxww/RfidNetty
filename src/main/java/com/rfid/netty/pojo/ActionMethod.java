package com.rfid.netty.pojo;

import java.lang.reflect.Method;

public class ActionMethod {
	private Method method;
	private String objId;
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
}
