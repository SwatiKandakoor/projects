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
public class ProjectRepositoryImpl implements ProjectRepository {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public ProjectRepositoryImpl() {
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
	public Integer checkDuplicates(String userId, String email, long mobile) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("checkdupl");

			query.setParameter("userId", userId);
			query.setParameter("userEmail", email);
			query.setParameter("userMobile", mobile);
			int count = ((Long) query.getSingleResult()).intValue();
			log.info("duplicates value size" + count);

			return count;

		} finally {
			manager.close();
		}
	}

	@Override
	public List<SignUpEntity> signIn(String userId, String password) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("signIn");
			query.setParameter("userId", userId);
			query.setParameter("userPassword", password);
			List<SignUpEntity> list = query.getResultList();
			return list;

		} finally {
			manager.close();
		}

	}

}
