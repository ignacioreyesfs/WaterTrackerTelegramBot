package com.ireyes.telegrambot.watertracker.model.processing.core;

import java.time.LocalDate;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.ireyes.telegrambot.watertracker.model.processing.processors.ErrorMessageProcessor;
import com.ireyes.telegrambot.watertracker.model.processing.processors.RecordMessageProcessor;
import com.ireyes.telegrambot.watertracker.model.processing.processors.StartMessageProcessor;
import com.ireyes.telegrambot.watertracker.model.record.RecordParser;

public class MessageProcessorFactory {
	private MessageProcessorFactory() {}
	
	public static MessageProcessor of(Update update) {
		String message = update.getMessage().getText().trim().toLowerCase();
		if(message.equals("/start")) {
			return new StartMessageProcessor(update.getMessage().getChatId());
		}
		
		if(message.equals("/today")) {
			return new AnalyticsMessageProccesor(update.getMessage().getChatId(), LocalDate.now(), LocalDate.now());
		}
		
		if(RecordParser.isDrinkRecord(message)) {
			return new RecordMessageProcessor(update);
		}
		
		return new ErrorMessageProcessor(update);
	}
	
}
