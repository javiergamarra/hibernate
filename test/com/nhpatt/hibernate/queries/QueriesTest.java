package com.nhpatt.hibernate.queries;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.nhpatt.hibernate.user.PersistUsersTest;
import com.nhpatt.hibernate.user.User;

public class QueriesTest extends PersistUsersTest {

	@Test
	public void criteriaQueryTest() {
		saveNewUser();

		Session session = getSession();
		session.beginTransaction();

		List<User> users = session.createCriteria(User.class).list();

		session.getTransaction().commit();
		session.close();
		assertTrue(!users.isEmpty());
	}

	@Test
	public void criteriaQueryWithRestrictionTest() {
		saveNewUser();

		Session session = getSession();
		session.beginTransaction();

		List<User> users = session.createCriteria(User.class)
				.add(Restrictions.ilike("name", "luis", MatchMode.ANYWHERE))
				.list();

		session.getTransaction().commit();
		session.close();
		assertTrue(!users.isEmpty());
	}

	@Test
	public void criteriaWithAliasTest() {
		saveNewUser();

		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		criteria.createAlias("petitions", "petitions");
		criteria.add(Restrictions.eq("petitions.id", 0));
		List<User> users = criteria.list();

		session.getTransaction().commit();
		session.close();
		assertTrue(users.isEmpty());
	}

	@Test
	public void queryByExampleTest() {
		saveNewUser();

		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Example.create(new User("Luis", "Garc�a")));
		List<User> users = criteria.list();

		session.getTransaction().commit();
		session.close();
		assertTrue(!users.isEmpty());
	}

	@Test
	public void hqlQueryTest() {
		saveNewUser();

		Session session = getSession();
		session.beginTransaction();

		List<User> users = session.createQuery("from User").list();

		session.getTransaction().commit();
		session.close();
		assertTrue(!users.isEmpty());
	}

	@Test
	public void sqlQueryTest() {
		saveNewUser();

		Session session = getSession();
		session.beginTransaction();

		List<Object[]> users = session.createSQLQuery("select * from User")
				.list();

		session.getTransaction().commit();
		session.close();
		assertTrue(!users.isEmpty());
	}

	@Test
	public void sqlQueryWithConversionTest() {
		saveNewUser();

		Session session = getSession();
		session.beginTransaction();

		List<User> users = session.createSQLQuery("select * from User")
				.addEntity(User.class).list();

		session.getTransaction().commit();
		session.close();
		assertTrue(!users.isEmpty());
	}

}
