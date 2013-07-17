package com.nhpatt.hibernate.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.hibernate.FlushMode;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.junit.Assert;
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

	@Test
	public void loadAndChangeValueWithoutFlushTest() {
		User user = saveUser("Pepe", "Rayuela");

		Session session = getSession();
		session.beginTransaction();
		session.setFlushMode(FlushMode.MANUAL);
		User userToChange = (User) session.get(User.class, user.getId());
		userToChange.setName("Lola");
		session.getTransaction().commit();
		session.close();

		session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		assertEquals("Pepe", userFromBD.getName());
		session.close();

	}

	@Test
	public void identityIsNotGuaranteedOutsideSessionTest() {
		User pepe = saveUser("Pepe", "Rayuela");

		Session session = getSession();
		User userFromBD = (User) session.get(User.class, pepe.getId());
		assertFalse(userFromBD == pepe);
		session.close();

	}

	@Test(expected = NonUniqueObjectException.class)
	public void aDifferentObjectWithTheSameIdentifierTest() {
		User pepe = saveUser("Pepe", "Rayuela");
		pepe.setName("Lola");
		Session session = getSession();
		User userFromBD = (User) session.get(User.class, pepe.getId());
		session.update(pepe);
		assertFalse(userFromBD == pepe);
		session.close();

	}

	public void flushAndRollbackTest() {
		Session session = getSession();
		session.setFlushMode(FlushMode.MANUAL);
		session.beginTransaction();

		User user = new User("Pepe", "Rayuela");
		session.save(user);

		session.getTransaction().rollback();
		session.close();

		session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		Assert.assertNull(userFromBD);
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
