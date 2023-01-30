package com.ireyes.telegrambot.watertracker.model.processing.processors.record;

public enum Unit {
	ML("ml");
	
	private String text;
	
	Unit(String text) {
		this.text = text;
	}
	
	public String getAsText() {
		return text;
	}
}
