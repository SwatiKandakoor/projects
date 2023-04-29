package com.xworkz.cm.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.cm.entity.TechnolgyListEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TechnologyRepositoryImpl implements TechnologyRepository {

	@Autowired
	private EntityManagerFactory managerFactory;

	@Override
	public boolean add(TechnolgyListEntity entity) {
		log.info("add in TechnologyRepoImpl");
		EntityManager entityManager = this.managerFactory.createEntityManager();
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
			return true;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return false;

	}

}
