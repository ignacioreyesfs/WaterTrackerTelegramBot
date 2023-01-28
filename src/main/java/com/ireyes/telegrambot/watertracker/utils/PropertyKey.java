package com.ireyes.telegrambot.watertracker.utils;

public enum PropertyKey {
	BOT_TOKEN("bot.token"),
	BOT_NAME("bot.name");
	
	private final String key;
	
	PropertyKey(String key){
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
}
