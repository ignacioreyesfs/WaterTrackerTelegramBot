package com.ireyes.telegrambot.watertracker.model.processing.core;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.ireyes.telegrambot.watertracker.model.processing.processors.ErrorMessageProcessor;
import com.ireyes.telegrambot.watertracker.model.processing.processors.StartMessageProcessor;
import com.ireyes.telegrambot.watertracker.model.processing.processors.record.RecordMessageProcessor;
import com.ireyes.telegrambot.watertracker.model.processing.processors.record.RecordParser;

public class MessageProcessorFactory {
	private MessageProcessorFactory() {}
	
	public static MessageProcessor of(Update update) {
		String message = update.getMessage().getText().trim().toLowerCase();
		if(message.startsWith("/start")) {
			return new StartMessageProcessor(update.getMessage().getChatId());
		}
		
		if(RecordParser.isDrinkRecord(message)) {
			return new RecordMessageProcessor(update);
		}
		
		return new ErrorMessageProcessor(update);
	}
	
}
