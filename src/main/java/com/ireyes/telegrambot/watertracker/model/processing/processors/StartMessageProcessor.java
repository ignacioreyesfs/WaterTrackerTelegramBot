package com.ireyes.telegrambot.watertracker.model.processing.processors;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.ireyes.telegrambot.watertracker.model.processing.core.MessageProcessor;
import com.ireyes.telegrambot.watertracker.utils.PropertyKey;
import com.ireyes.telegrambot.watertracker.utils.PropertyReader;

public class StartMessageProcessor implements MessageProcessor {
	Long chatId;
	
	public StartMessageProcessor(Long chatId) {
		this.chatId = chatId;
	}

	@Override
	public SendMessage process() {
		SendMessage message = new SendMessage();
		message.setChatId(chatId);
		message.setParseMode("MarkdownV2");
		message.setText(PropertyReader.getProperty(PropertyKey.MESSAGE_START.getKey()));
		return message;
	}

}
