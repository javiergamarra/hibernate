package com.nhpatt.hibernate.user;

import static org.junit.Assert.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.nhpatt.hibernate.utils.HibernateTest;

public class RoleTest extends HibernateTest {

	// public Role saveRole() {
	// Session session = getSession();
	// session.getTransaction().begin();
	// Role role = new Role("Administrador");
	// session.persist(role);
	//
	// session.getTransaction().commit();
	// session.close();
	// return role;
	// }
	//
	//@Test
	//public void persistARoleTest() {
	//Role role = saveRole();
	//assertNotNull(role);
	//}
	// 

	@Test
	public void aUserHasARoleTest() {
		Role role = saveRole();

		Session session = getSession();
		session.getTransaction().begin();
		Integer idUser = (Integer) session
				.save(new User("Luis", "García", role));
		session.getTransaction().commit();
		session.close();

		session = getSession();
		User user = (User) session.load(User.class, idUser);
//		User user = (User) session.get(User.class, idUser);
		user.getRole();
		session.close();

		assertNotNull(user.getRole());
	}

	// @Test
	// public void aUserPersistsHisRoleTest() {
	// String roleName = "Usuario";
	//
	// Session session = getSession();
	// session.getTransaction().begin();
	// Role role = new Role(roleName);
	// Integer idUser = (Integer) session
	// .save(new User("Luis", "García", role));
	// session.getTransaction().commit();
	// session.close();
	//
	// assertNotNull(idUser);
	//
	// session = getSession();
	// User user = (User) session.load(User.class, idUser);
	// assertNotNull(user.getRole());
	// assertEquals(roleName, user.getRole().getName());
	//
	// }
}
