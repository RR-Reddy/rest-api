package com.msg.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class GameRoom {

	private long id;
	private List<Player> playerList;
	private GameRoomStatus gameRoomStatus;
	private List<Msg> msgList;
	private boolean[] isNotifiedToPlayer = new boolean[] { false, false };
	private LocalDateTime[] lastRefreshTime = new LocalDateTime[2];

	public boolean[] getIsNotifiedToPlayer() {
		return isNotifiedToPlayer;
	}

	public void setIsNotifiedToPlayer(boolean[] isNotifiedToPlayer) {
		this.isNotifiedToPlayer = isNotifiedToPlayer;
	}

	public LocalDateTime[] getLastRefreshTime() {
		return lastRefreshTime;
	}

	public void setLastRefreshTime(LocalDateTime[] lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}

	public List<Msg> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<Msg> msgList) {
		this.msgList = msgList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	public GameRoomStatus getGameRoomStatus() {
		return gameRoomStatus;
	}

	public void setGameRoomStatus(GameRoomStatus gameRoomStatus) {
		this.gameRoomStatus = gameRoomStatus;
	}

	@Override
	public String toString() {
		return "GameRoom [id=" + id + ", playerList=" + playerList + ", gameRoomStatus=" + gameRoomStatus + ", msgList="
				+ msgList + ", isNotifiedToPlayer=" + Arrays.toString(isNotifiedToPlayer) + ", lastRefreshTime="
				+ Arrays.toString(lastRefreshTime) + "]";
	}

}
