package com.nhpatt.hibernate.hellodatabase;

import static junit.framework.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.Before;

public class HelloDatabaseJPAWithHibernateTest {

	private EntityManagerFactory entityManagerFactory;
	private static final String DATASOURCE = "test";

	@org.junit.Test
	public void selectFirstObjectFromTableTestTest() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Session session = (Session) entityManager.getDelegate();
		Test test = (Test) session.get(Test.class, 1);
		session.close();

		assertEquals("1 - Hi", test.toString());
	}

	@Before
	public void setUp() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory(DATASOURCE);
	}
}
