package com.ireyes.telegrambot.watertracker.dao;

import com.ireyes.telegrambot.watertracker.model.record.DrinkRecord;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DrinkRecordDao {
	private EntityManager em;
	private EntityManagerFactory emf;
	private static DrinkRecordDao instance;
	
	public static DrinkRecordDao getInstance() {
		if(instance == null) {
			instance = new DrinkRecordDao();
		}
		return instance;
	}
	
	public DrinkRecordDao() {
		emf = Persistence.createEntityManagerFactory("persistence");
	}
	
	public void save(DrinkRecord drinkRecord) {
		beginTransaction();
		
		if(drinkRecord.getId() == null) {
			em.persist(drinkRecord);
		}else {
			em.merge(drinkRecord);
		}
		
		endTransaction(true);
	}
	
	public DrinkRecord findById(Long id) {
		return em.find(DrinkRecord.class, id);
	}
	
	public void delete(DrinkRecord drinkRecord) {
		em.remove(drinkRecord);
	}
	
	private void beginTransaction() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	private void endTransaction(boolean commmit) {
		if(commmit) {
			em.getTransaction().commit();
		}else {
			em.getTransaction().rollback();
		}
		em.close();
	}
}
