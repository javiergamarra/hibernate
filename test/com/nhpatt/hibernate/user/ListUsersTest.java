package com.nhpatt.hibernate.user;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.junit.Test;

import com.nhpatt.hibernate.utils.HibernateTest;

public class ListUsersTest extends HibernateTest {

	private static final int ID = 1;

	@Test
	public void selectFirstObjectFromTableUserTest() {
		Session session = getSession();
		User user = (User) session.get(User.class, ID);
		session.close();
		assertNotNull(user);
		assertNotNull(user.getName());
	}

	@Test
	public void selectSurnameFromTableUserTest() {
		Session session = getSession();
		User user = (User) session.get(User.class, ID);
		session.close();
		assertNotNull(user);
		assertNotNull(user.getSurname());
	}

	@Test
	public void selectTransientTest() {
		Session session = getSession();
		User user = (User) session.get(User.class, ID);
		session.close();
		assertNotNull(user);
		assertNotNull(user.getName() + user.getSurname(), user.getFullName());
	}

}
