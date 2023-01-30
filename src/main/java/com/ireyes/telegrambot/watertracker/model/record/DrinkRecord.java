package com.ireyes.telegrambot.watertracker.model.record;

import java.time.LocalDateTime;

import com.ireyes.telegrambot.watertracker.model.processing.processors.record.Unit;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DRINK_RECORD")
public class DrinkRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long chatId;
	private long quantity;
	@Enumerated(EnumType.STRING)
	private Unit unit;
	private LocalDateTime dateTime;
	
	public DrinkRecord() {}
	
	public DrinkRecord(long chatId, long quantity, Unit unit, LocalDateTime dateTime) {
		this.chatId = chatId;
		this.quantity = quantity;
		this.unit = unit;
		this.dateTime = dateTime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
