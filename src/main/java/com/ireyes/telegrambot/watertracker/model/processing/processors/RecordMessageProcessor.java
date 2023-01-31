package com.ireyes.telegrambot.watertracker.model.processing.processors;

import java.time.LocalDateTime;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ireyes.telegrambot.watertracker.dao.DrinkRecordDao;
import com.ireyes.telegrambot.watertracker.model.processing.core.MessageProcessor;
import com.ireyes.telegrambot.watertracker.model.record.DrinkRecord;
import com.ireyes.telegrambot.watertracker.model.record.RecordParser;

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
				, Long.parseLong(RecordParser.getQuantity(message))
				, RecordParser.getUnit(message), LocalDateTime.now());
		
		dao.save(drink);
		
		return new SendMessage(update.getMessage().getChatId().toString(), "Record added");
	}

}
