package com.ireyes.telegrambot.watertracker.model.processing.processors;

import java.time.LocalDateTime;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ireyes.telegrambot.watertracker.dao.DrinkRecordDao;
import com.ireyes.telegrambot.watertracker.model.processing.core.MessageProcessor;
import com.ireyes.telegrambot.watertracker.model.record.DrinkRecord;

public class RecordMessageProcessor implements MessageProcessor {
	private Update update;
	private DrinkRecordDao dao;
	
	public RecordMessageProcessor(Update update) {
		this.update = update;
		this.dao = DrinkRecordDao.getInstance();
	}

	@Override
	public SendMessage process() {
		String message = update.getMessage().getText();
		
		DrinkRecord drink = new DrinkRecord(update.getMessage().getChatId()
				, getQuantity(message), getUnit(message), LocalDateTime.now());
		
		dao.save(drink);
		
		return new SendMessage(update.getMessage().getChatId().toString(), "Record added");
	}
	
	private long getQuantity(String message) {
		int i;
		
		for(i = 0; i < message.length() && Character.isDigit(message.charAt(i)); i++);
		
		if(i == 0) {
			throw new InvalidFormatException(message);
		}
		
		return Long.parseLong(message.substring(0, i));
	}
	
	private String getUnit(String message) {
		int i;
		
		for(i = message.length() - 1; i >= 0 && !Character.isDigit(message.charAt(i)); i--);
		
		if(i < 0 || i == message.length() - 1) {
			throw new InvalidFormatException(message);
		}
		
		return message.substring(i+1);
	}

}
