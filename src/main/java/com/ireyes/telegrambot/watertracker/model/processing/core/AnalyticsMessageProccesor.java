package com.ireyes.telegrambot.watertracker.model.processing.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.ireyes.telegrambot.watertracker.dao.DrinkRecordDao;

public class AnalyticsMessageProccesor implements MessageProcessor {
	private Long chatId;
	private LocalDate start;
	private LocalDate finish;

	public AnalyticsMessageProccesor(Long chatId, LocalDate start, LocalDate finish) {
		this.chatId = chatId;
		this.start = start;
		this.finish = finish;
	}

	@Override
	public SendMessage process() {
		SendMessage message = new SendMessage();
		DrinkRecordDao dao = DrinkRecordDao.getInstance();
		Long total = dao.findBetweenDates(chatId, LocalDateTime.of(start, LocalTime.MIN), 
				LocalDateTime.of(finish, LocalTime.MAX))
				.stream()
				.map(drinkRecord -> drinkRecord.getQuantity() * drinkRecord.getUnit().getMlRatio())
				.reduce(0L, (accumm, quantity) -> accumm + quantity);
		
		message.setChatId(chatId);
		message.setText("Total consumed: " + total + "ml."
				+ "\nIt is " + ((total*100)/2000) + "% of the recommended daily consumption (2L)");
		
		return message;
	}

}
