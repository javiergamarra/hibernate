package com.nhpatt.hibernate.user;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.junit.Test;

public class ListUsersTest extends PersistUsersTest {

	@Test
	public void selectFirstObjectFromTableUserTest() {

		User user = saveNewUser();

		Session session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		session.close();
		assertNotNull(userFromBD);
		assertNotNull(userFromBD.getName());
	}

	@Test
	public void selectSurnameFromTableUserTest() {
		User user = saveNewUser();

		Session session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		session.close();
		assertNotNull(userFromBD);
		assertNotNull(userFromBD.getSurname());
	}

	@Test
	public void selectTransientTest() {
		User user = saveNewUser();

		Session session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		session.close();
		assertNotNull(userFromBD);
		assertNotNull(userFromBD.getName() + userFromBD.getSurname(),
				userFromBD.getFullName());
	}

}
