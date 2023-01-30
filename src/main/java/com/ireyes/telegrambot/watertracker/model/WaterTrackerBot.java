package com.ireyes.telegrambot.watertracker.model;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.ireyes.telegrambot.watertracker.model.processing.core.MessageProcessor;
import com.ireyes.telegrambot.watertracker.model.processing.core.MessageProcessorFactory;
import com.ireyes.telegrambot.watertracker.utils.PropertyKey;
import com.ireyes.telegrambot.watertracker.utils.PropertyReader;

public class WaterTrackerBot extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {
		SendMessage message;
		MessageProcessor messageProcessor;
		
		if(!update.hasMessage() || !update.getMessage().hasText()) {
			return;
		}
		
		try {
			messageProcessor = MessageProcessorFactory.of(update);
			
			message = messageProcessor.process();
			execute(message);
			
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return PropertyReader.getProperty(PropertyKey.BOT_NAME.getKey());
	}

	@Override
	public String getBotToken() {
		return PropertyReader.getProperty(PropertyKey.BOT_TOKEN.getKey());
	}
	
}
