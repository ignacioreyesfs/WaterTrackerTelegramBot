package com.ireyes.telegrambot.watertracker.model.processing.processors;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ireyes.telegrambot.watertracker.model.processing.core.MessageProcessor;
import com.ireyes.telegrambot.watertracker.utils.PropertyKey;
import com.ireyes.telegrambot.watertracker.utils.PropertyReader;

public class ErrorMessageProcessor implements MessageProcessor {
	private Update update;
	
	public ErrorMessageProcessor(Update update) {
		this.update = update;
	}
	
	@Override
	public SendMessage process() {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId());
		message.setText(PropertyReader.getProperty(PropertyKey.MESSAGE_UNKNOWN.getKey()));
		return message;
	}

}
