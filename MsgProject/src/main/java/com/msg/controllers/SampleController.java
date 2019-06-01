package com.msg.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msg.domain.Msg;
import com.msg.domain.Player;
import com.msg.service.GameService;

@RestController
public class SampleController {

	private Logger logger = LoggerFactory.getLogger(SampleController.class);

	@Autowired
	private GameService gameService;

	// join or create room
	@PostMapping("/create-room")
	public Long createRoom(@RequestBody Player player) {

		return gameService.createGameRoom(player);

	}

	@GetMapping("/find-opponent")
	public Player findOpponent(@RequestParam Long gameRoomId, @RequestParam Long playerId) {
		Optional<Player> player = gameService.findOpponent(gameRoomId, playerId);

		return player.isPresent() ? player.get() : null;

	}

	@GetMapping("/can-start-game")
	public boolean canStartGame(@RequestParam Long gameRoomId, @RequestParam Long playerId) {
		boolean val = gameService.canStartGame(gameRoomId, playerId);
		return val;
	}

	@PutMapping("/send-msg")
	public void sendMsg(@RequestBody Msg msg, @RequestParam Long gameRoomId, @RequestParam Long playerId) {

		gameService.sendMsg(msg, gameRoomId, playerId);
	}

	@GetMapping("/receive-msg")
	public Msg receiveMsg(@RequestParam Long gameRoomId, @RequestParam Long playerId) {

		Optional<Msg> msg = gameService.receiveMsg(gameRoomId, playerId);
		return msg.isPresent() ? msg.get() : null;
	}

	@GetMapping("/game-end")
	public void onGameEnd(@RequestParam Long gameRoomId, @RequestParam Long playerId) {
		gameService.onGameEnd(gameRoomId, playerId);
	}

	// get disconnect status as boolean
	@GetMapping("/refresh")
	public boolean onRefresh(@RequestParam Long gameRoomId, @RequestParam Long playerId) {
		return gameService.onRefresh(gameRoomId, playerId);
	}

	@GetMapping("/log")
	public void printGameRoomMap() {
		gameService.printGameRoomMap();
	}

}
