package com.msg.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.msg.domain.GameRoom;
import com.msg.domain.GameRoomStatus;
import com.msg.domain.Msg;
import com.msg.domain.Player;
import com.msg.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	private Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

	private Vector<GameRoom> singlePlayerGameRooms = new Vector<GameRoom>();
	private Map<Long, GameRoom> gameRoomsMap = new ConcurrentHashMap<Long, GameRoom>();

	public Optional<GameRoom> getGameRoom(Long roomKey) {
		return Optional.ofNullable(gameRoomsMap.get(roomKey));

	}

	// @Override
	public Long createGameRoom(Player player) {

		if (singlePlayerGameRooms.isEmpty()) {

			// create new Game room

			long gameRoomId = System.currentTimeMillis();

			GameRoom gameRoom = new GameRoom();
			gameRoom.setId(gameRoomId);
			gameRoom.setGameRoomStatus(GameRoomStatus.WAITING);
			gameRoom.setMsgList(new ArrayList<Msg>());

			player.setFirstPlayer(true);
			List<Player> playerList = new ArrayList<Player>(2);
			playerList.add(player);

			gameRoom.setPlayerList(playerList);

			gameRoomsMap.put(gameRoomId, gameRoom);
			singlePlayerGameRooms.add(gameRoom);
			return gameRoom.getId();

		} else {

			// return existing game room

			GameRoom gameRoom = singlePlayerGameRooms.get(0);
			gameRoom.getPlayerList().add(player);
			gameRoom.setGameRoomStatus(GameRoomStatus.READY);

			singlePlayerGameRooms.remove(gameRoom);
			return gameRoom.getId();

		}

	}

	// @Override
	public Optional<Player> findOpponent(Long gameRoomId, Long playerId) {

		GameRoom gameRoom = gameRoomsMap.get(gameRoomId);

		if (gameRoom.getGameRoomStatus() != GameRoomStatus.READY) {
			return Optional.empty();
		}

		List<Player> playerList = gameRoom.getPlayerList();

		boolean isFirstPlayer = (playerList.get(0).getId() == playerId);

		boolean[] array = gameRoom.getIsNotifiedToPlayer();

		array[isFirstPlayer ? 0 : 1] = true;

		// update game room status to in progress if both players notified
		if (array[0] && array[1]) {
			gameRoom.setGameRoomStatus(GameRoomStatus.IN_PROGRESS);
		}

		return Optional.of(playerList.get(isFirstPlayer ? 1 : 0));

	}

	// @Override
	public boolean canStartGame(Long gameRoomId, Long playerId) {

		GameRoom gameRoom = gameRoomsMap.get(gameRoomId);
		return gameRoom.getGameRoomStatus() == GameRoomStatus.IN_PROGRESS;

	}

	// @Override
	public void sendMsg(Msg msg, Long gameRoomId, Long playerId) {

		msg.setFromPlayerId(playerId);

		GameRoom gameRoom = gameRoomsMap.get(gameRoomId);
		gameRoom.getMsgList().add(msg);

	}

//@Override
	public Optional<Msg> receiveMsg(Long gameRoomId, Long playerId) {

		GameRoom gameRoom = gameRoomsMap.get(gameRoomId);
		List<Msg> msgList = gameRoom.getMsgList();

		if (msgList.isEmpty()) {
			return Optional.empty();
		}

		Msg selectedMsg = null;

		for (Msg msg : msgList) {

			if (msg.getFromPlayerId() != playerId) {
				selectedMsg = msg;
				break;
			}

		}
		msgList.remove(selectedMsg);
		return Optional.ofNullable(selectedMsg);

	}

	// @Override
	public void onGameEnd(Long gameRoomId, Long playerId) {

		GameRoom gameRoom = gameRoomsMap.get(gameRoomId);
		gameRoom.setGameRoomStatus(GameRoomStatus.FINISHED);

	}

	// @Override
	public boolean onRefresh(Long gameRoomId, Long playerId) {

		GameRoom gameRoom = gameRoomsMap.get(gameRoomId);
		boolean isFirstPlayer = (gameRoom.getPlayerList().get(0).getId() == playerId);
		gameRoom.getLastRefreshTime()[isFirstPlayer ? 0 : 1] = LocalDateTime.now();

		return gameRoom.getGameRoomStatus() == GameRoomStatus.DISCONNECTED;

	}
	
	//@Override
	public void printGameRoomMap() {
		logger.info("================================");
		logger.info(" log url : {}",gameRoomsMap);
		logger.info("================================");
	}

}
