package com.msg.domain;

import java.util.Arrays;

public class Msg {

	private long toPlayerId;
	private long fromPlayerId;
	private byte[] msgData;

	public long getToPlayerId() {
		return toPlayerId;
	}

	public void setToPlayerId(long toPlayerId) {
		this.toPlayerId = toPlayerId;
	}

	public long getFromPlayerId() {
		return fromPlayerId;
	}

	public void setFromPlayerId(long fromPlayerId) {
		this.fromPlayerId = fromPlayerId;
	}

	public byte[] getMsgData() {
		return msgData;
	}

	public void setMsgData(byte[] msgData) {
		this.msgData = msgData;
	}

	@Override
	public String toString() {
		return "Msg [toPlayerId=" + toPlayerId + ", fromPlayerId=" + fromPlayerId + ", msgData="
				+ Arrays.toString(msgData) + "]";
	}

}
