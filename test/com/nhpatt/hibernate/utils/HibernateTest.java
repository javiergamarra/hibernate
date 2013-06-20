package com.nhpatt.hibernate.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;

public class HibernateTest {

	protected static final String DATASOURCE = "test";
	protected EntityManagerFactory entityManagerFactory;

	@Before
	public void setUp() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory(DATASOURCE);
	}

	@After
	public void tearDown() {
		entityManagerFactory.close();
	}

	protected Session getSession() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Session session = (Session) entityManager.getDelegate();
		return session;
	}

}
