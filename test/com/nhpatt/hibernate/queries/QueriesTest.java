package com.nhpatt.hibernate.queries;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
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


}
