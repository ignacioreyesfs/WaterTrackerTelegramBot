package com.ireyes.telegrambot.watertracker.model.processing.core;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface MessageProcessor {
	public SendMessage process();
}
