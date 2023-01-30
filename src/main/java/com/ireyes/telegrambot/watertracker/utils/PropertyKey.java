package com.ireyes.telegrambot.watertracker.utils;

public enum PropertyKey {
	BOT_TOKEN("bot.token"),
	BOT_NAME("bot.name"),
	MESSAGE_START("bot.message.start"),
	MESSAGE_UNKNOWN("bot.message.unknown");
	
	private final String key;
	
	PropertyKey(String key){
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
}
