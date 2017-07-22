package com.rfid.netty.pojo;

public enum MessageType {

	SERVICE_REQ((byte) 0), SERVICE_RESP((byte) 1), ONE_WAY((byte) 2),
	LOGIN_REQ((byte) 3), LOGIN_RESP((byte) 4), HEARTBEAT_REQ((byte) 5),
	HEARTBEAT_RESP((byte) 6), READ_REQ((byte) 7), READ_RESP((byte) 8), WANTED_REQ((byte) 9),
	WANTED_RESP((byte) 10), FIND_REQ((byte) 11), FIND_RESP((byte) 12);

	private byte value;

	private MessageType(byte value) {
		this.value = value;
	}

	public byte value() {
		return this.value;
	}
}
