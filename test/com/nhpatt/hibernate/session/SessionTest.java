package com.nhpatt.hibernate.session;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.junit.Test;

import com.nhpatt.hibernate.user.User;
import com.nhpatt.hibernate.utils.HibernateTest;

public class SessionTest extends HibernateTest {

	@Test
	public void saveWithoutExplicitCallTest() {
		Session session = getSession();
		session.beginTransaction();

		User user = new User("Pepe", "Rayuela");
		session.save(user);
		user.setName("Lola");

		session.getTransaction().commit();
		session.close();

		session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		assertEquals("Lola", userFromBD.getName());
		session.close();

	}

	@Test
	public void loadAndChangeValueTest() {
		User user = saveUser("Pepe", "Rayuela");

		Session session = getSession();
		session.beginTransaction();
		User userToChange = (User) session.get(User.class, user.getId());
		userToChange.setName("Lola");
		session.getTransaction().commit();
		session.close();

		session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		assertEquals("Lola", userFromBD.getName());
		session.close();

	}

	private User saveUser(String name, String surname) {
		Session session = getSession();
		session.beginTransaction();

		User user = new User(name, surname);
		session.save(user);

		session.getTransaction().commit();
		session.close();
		return user;
	}

}
