package com.ireyes.telegrambot.watertracker;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.ireyes.telegrambot.watertracker.model.WaterTrackerBot;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
    		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new WaterTrackerBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
    }
}
