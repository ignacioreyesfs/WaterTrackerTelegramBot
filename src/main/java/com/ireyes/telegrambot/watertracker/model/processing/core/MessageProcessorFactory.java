package com.ireyes.telegrambot.watertracker.model.processing.core;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.ireyes.telegrambot.watertracker.model.processing.processors.ErrorMessageProcessor;
import com.ireyes.telegrambot.watertracker.model.processing.processors.RecordMessageProcessor;
import com.ireyes.telegrambot.watertracker.model.processing.processors.StartMessageProcessor;

public class MessageProcessorFactory {
	private MessageProcessorFactory() {}
	
	public static MessageProcessor of(Update update) {
		String message = update.getMessage().getText().trim().toLowerCase();
		if(message.startsWith("/start")) {
			return new StartMessageProcessor(update.getMessage().getChatId());
		}
		
		if(isNewRecord(message)) {
			return new RecordMessageProcessor(update);
		}
		
		return new ErrorMessageProcessor(update);
	}
	
	private static boolean isNewRecord(String message) {
		boolean hasNumber = true;
		
		if(message.length() < 3) {
			return false;
		}
		
		try {
			Integer.parseInt(message.substring(0, message.length()-2));
		}catch(Exception e) {
			hasNumber = false;
		}
		
		return hasNumber && message.endsWith("ml");
	}
}
