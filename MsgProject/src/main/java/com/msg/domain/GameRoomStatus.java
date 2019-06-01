package com.msg.domain;

public enum GameRoomStatus {

	WAITING(1, "waiting"), READY(2, "ready"), IN_PROGRESS(3, "in-progress"), DISCONNECTED(4, "disconnected"),
	FINISHED(5, "finished");

	private int statusId;
	private String status;

	private GameRoomStatus(int statusId, String status) {

		this.statusId = statusId;
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
