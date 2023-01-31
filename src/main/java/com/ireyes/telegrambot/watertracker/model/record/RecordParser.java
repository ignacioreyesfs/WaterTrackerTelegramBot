package com.ireyes.telegrambot.watertracker.model.record;

import com.ireyes.telegrambot.watertracker.model.processing.processors.InvalidFormatException;

public class RecordParser {
	public static boolean isDrinkRecord(String text) {
		boolean valid = true;
		
		try {
			getUnit(text);
		}catch (Exception e) {
			valid = false;
		}
		
		return valid;
	}
	
	public static Unit getUnit(String text) {
		String quantity = getQuantity(text);
		String unitFromText = text.substring(quantity.length());
		
		for(Unit unit: Unit.values()) {
			if(unit.getAsText().equals(unitFromText)) {
				return unit;
			}
		}
		
		throw new InvalidFormatException("Cannot find unit in: " + text);
	}
	
	public static String getQuantity(String text) {
		int i;
		
		for(i = 0; i < text.length() && Character.isDigit(text.charAt(i)); i++);
		
		if(i == 0) {
			throw new InvalidFormatException("Cannot find quantity in: " + text);
		}
		
		return text.substring(0, i);
	}
}
