package com.ireyes.telegrambot.watertracker.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ireyes.telegrambot.watertracker.dao.DrinkRecordDao;
import com.ireyes.telegrambot.watertracker.model.processing.core.MessageProcessorFactory;

import jakarta.persistence.Persistence;

public class RecordProcessorTest {
	@After
	public void cleanUp() {
		Persistence.createEntityManagerFactory("persistence").createEntityManager().clear();
	}
	
	@Test
	public void newRecordFail() {
		DrinkRecordDao dao = DrinkRecordDao.getInstance();
		Update update = createUpdate(1L, "notrecord");
		MessageProcessorFactory.of(update).process();
		assertEquals(0, dao.count().longValue());
	}
	
	@Test
	public void newRecordOk() {
		DrinkRecordDao dao = DrinkRecordDao.getInstance();
		Update update = createUpdate(1L, "100ml");
		MessageProcessorFactory.of(update).process();
		assertEquals(1, dao.count().longValue());
	}
	
	private Update createUpdate(Long chatId, String text) {
		Update update = new Update();
		Message message = new Message();
		Chat chat = new Chat();
		chat.setId(chatId);
		message.setChat(chat);
		message.setText(text);
		update.setMessage(message);
		return update;
	}
}
