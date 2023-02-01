package com.ireyes.telegrambot.watertracker.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.ireyes.telegrambot.watertracker.model.record.DrinkRecord;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
		DrinkRecord record;
		em = emf.createEntityManager();
		record = em.find(DrinkRecord.class, id);
		em.close();
		return record;
	}
	
	public List<DrinkRecord> findBetweenDates(Long chatId, LocalDateTime start, LocalDateTime finish){
		List<DrinkRecord> records;
		CriteriaBuilder cb;
		CriteriaQuery<DrinkRecord> cr;
		Root<DrinkRecord> root;
		Predicate[] predicates = new Predicate[2];
		em = emf.createEntityManager();
		
				
		cb = em.getCriteriaBuilder();
		cr = cb.createQuery(DrinkRecord.class);
		root = cr.from(DrinkRecord.class);
		
		predicates[0] = cb.between(root.get("dateTime"), start, finish);
		predicates[1] = cb.equal(root.get("chatId"), chatId);
		cr.select(root).where(predicates);
		
		records = em.createQuery(cr).getResultList();
		
		em.close();
		return records;
	}
	
	public void delete(DrinkRecord drinkRecord) {
		beginTransaction();
		em.remove(drinkRecord);
		endTransaction(true);
	}
	
	public Long count() {
		Long count;
		em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<DrinkRecord> root = cr.from(DrinkRecord.class);
		
		cr.select(cb.count(root));
		count = em.createQuery(cr).getSingleResult();
		
		em.close();
		
		return count;
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
