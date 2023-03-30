package com.xworkz.cm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.cm.entity.SignUpEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SignUpRepositoryImpl implements SignUpRepository{
   @Autowired
	private EntityManagerFactory entityManagerFactory;

	public SignUpRepositoryImpl() {
		log.info("create " + this.getClass().getSimpleName());
	}

	
	@Override
	public boolean save(SignUpEntity entity) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(entity);
		transaction.commit();
		manager.close();
		return true;
	}



	@Override
	public Integer checkDuplicates(String userId, String email, Double mobile) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("checkdupl");
			query.setParameter("userId", userId);
			query.setParameter("userEmail", email);
			query.setParameter("userMobile", mobile);
			int count =((Long) query.getSingleResult()).intValue();
			log.info("duplicates value size" + count);
		
			return count;

		} finally {
			manager.close();
		}		
	}

}
