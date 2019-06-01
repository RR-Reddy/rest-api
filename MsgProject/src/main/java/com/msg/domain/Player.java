package com.msg.domain;

import java.util.Arrays;

public class Player {

	private long id;
	private byte[] imageData;
	private String name;
	private boolean isFirstPlayer;

	public boolean isFirstPlayer() {
		return isFirstPlayer;
	}

	public void setFirstPlayer(boolean isFirstPlayer) {
		this.isFirstPlayer = isFirstPlayer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", imageData=" + Arrays.toString(imageData) + ", name=" + name + ", isFirstPlayer="
				+ isFirstPlayer + "]";
	}

}
