package com.nhpatt.hibernate.petition;

import static junit.framework.Assert.assertNotNull;
import junit.framework.Assert;

import org.hibernate.Session;
import org.junit.Test;

import com.nhpatt.hibernate.user.User;
import com.nhpatt.hibernate.utils.HibernateTest;

public class PetitionTest extends HibernateTest {

	@Test
	public void savePetitionsTest() {
		Session session = getSession();
		session.beginTransaction();

		Petition petition = new Petition();
		session.save(petition);

		session.getTransaction().commit();
		session.close();

		session = getSession();
		Petition petitionFromBD = (Petition) session.get(Petition.class,
				petition.getId());
		assertNotNull(petitionFromBD);
		session.close();
	}

	@Test
	public void savePetitionWithAUserAndloadPetitionTest() {
		User user = saveUserWithAPetition();

		Session session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		Assert.assertFalse(userFromBD.getPetitions().isEmpty());
		session.close();
	}

	// @Test(expected = LazyInitializationException.class)
	public void loadUserWithLazyPetitionsTest() {
		User user = saveUserWithAPetition();

		Session session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		session.close();
		Assert.assertFalse(userFromBD.getPetitions().isEmpty());
	}

	@Test
	public void loadUserWithEagerPetitionsTest() {
		User user = saveUserWithAPetition();

		Session session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		session.close();
		Assert.assertFalse(userFromBD.getPetitions().isEmpty());
	}

	@Test
	public void loadUserWithLazyButJoinPetitionsTest() {
		//Lazy Fetching in Join is, in fact, eager fetching
		User user = saveUserWithAPetition();

		Session session = getSession();
		User userFromBD = (User) session.get(User.class, user.getId());
		session.close();
		Assert.assertFalse(userFromBD.getPetitions().isEmpty());
	}

	private User saveUserWithAPetition() {
		Session session = getSession();
		session.beginTransaction();

		User user = new User("Javier", "Gamarra");
		Petition petition = new Petition();
		user.getPetitions().add(petition);

		session.save(petition);
		session.save(user);
		session.getTransaction().commit();
		session.close();
		return user;
	}

}
