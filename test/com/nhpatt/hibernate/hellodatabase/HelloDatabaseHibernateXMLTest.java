package com.nhpatt.hibernate.hellodatabase;

import static junit.framework.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Before;

public class HelloDatabaseHibernateXMLTest {

	private SessionFactory sessionFactory;

	@org.junit.Test
	public void selectFirstObjectFromTableTestTest() {
		Session session = sessionFactory.openSession();
		Test test = (Test) session.get(Test.class, 1);
		session.close();

		assertEquals("1 - Hi", test.toString());
	}

	@Before
	public void setUp() {
		Configuration configuration = new Configuration().configure();
		configuration.addClass(Test.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
}
