package com.nhpatt.hibernate.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.junit.Test;

import com.nhpatt.hibernate.utils.HibernateTest;

public class PersistUsersTest extends HibernateTest {

	protected User saveNewUser() {
		Session session = getSession();
		session.beginTransaction();
		User user = new User("Luis", "García");
		session.persist(user);
		session.getTransaction().commit();
		session.close();
		return user;
	}

	@Test
	public void saveUserWithGeneratedIdTest() {
		User user = saveNewUser();
		assertNotNull(user);
		assertNotNull(user.getId());
	}

	@Test
	public void updateUserTest() {
		User user = saveNewUser();
		String newCoolName = "Juan";

		Session session = getSession();
		session.beginTransaction();
		User updateUser = (User) session.get(User.class, user.getId());
		updateUser.setName(newCoolName);
		session.update(updateUser);
		session.getTransaction().commit();
		session.close();

		session = getSession();
		User updatedUser = (User) session.get(User.class, user.getId());
		session.close();

		assertNotNull(updatedUser);
		assertNotNull(newCoolName, updatedUser);
	}

	@Test
	public void deleteUserTest() {
		User user = saveNewUser();

		Session session = getSession();
		session.beginTransaction();
		User deleteUser = (User) session.get(User.class, user.getId());
		session.delete(deleteUser);
		session.getTransaction().commit();
		session.close();

		session = getSession();
		User deletedUser = (User) session.get(User.class, user.getId());
		session.close();

		assertNull(deletedUser);
	}

}
