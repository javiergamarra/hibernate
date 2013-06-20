package com.nhpatt.hibernate.hellodatabase;

import static junit.framework.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;

public class HelloDatabaseJPATest {

	private EntityManagerFactory entityManagerFactory;
	private static final String DATASOURCE = "test";

	@org.junit.Test
	public void selectFirstObjectFromTableTestTest() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Test test = entityManager.find(Test.class, 1);
		entityManager.close();

		assertEquals("1 - Hi", test.toString());
	}

	@Before
	public void setUp() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory(DATASOURCE);
	}
}
