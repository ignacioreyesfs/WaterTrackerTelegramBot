package com.ireyes.telegrambot.watertracker.model.record;

public enum Unit {
	ML("ml", 1);
	
	private String text;
	private int mlRatio;
	
	Unit(String text, int mlRatio) {
		this.text = text;
		this.mlRatio = mlRatio;
	}
	
	public String getAsText() {
		return text;
	}
	
	public int getMlRatio() {
		return mlRatio;
	}
}
