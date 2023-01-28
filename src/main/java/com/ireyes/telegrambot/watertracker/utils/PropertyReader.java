package com.ireyes.telegrambot.watertracker.utils;

import java.io.IOException;
import java.util.Properties;

import com.ireyes.telegrambot.watertracker.App;

public class PropertyReader {
	private static Properties prop;
	
	private PropertyReader() {}
	
	static {
		prop = new Properties();
		try {
			Properties secret = new Properties();
			Properties bot = new Properties();
			
			secret.load(App.class.getClassLoader().getResourceAsStream("secret.properties"));
			bot.load(App.class.getClassLoader().getResourceAsStream("bot.properties"));
			
			prop.putAll(secret);
			prop.putAll(bot);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
