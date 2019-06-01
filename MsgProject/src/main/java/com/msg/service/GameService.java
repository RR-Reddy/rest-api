package com.msg.service;

import java.util.Optional;

import com.msg.domain.GameRoom;
import com.msg.domain.Msg;
import com.msg.domain.Player;

public interface GameService {

	Long createGameRoom(Player player);

	Optional<Player> findOpponent(Long gameRoomId, Long playerId);

	boolean canStartGame(Long gameRoomId, Long playerId);

	void sendMsg(Msg msg, Long gameRoomId, Long playerId);

	Optional<Msg> receiveMsg(Long gameRoomId, Long playerId);

	void onGameEnd(Long gameRoomId, Long playerId);

	boolean onRefresh(Long gameRoomId, Long playerId);

	void printGameRoomMap();


}
