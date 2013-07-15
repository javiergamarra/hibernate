package com.nhpatt.hibernate.owner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.junit.Test;

import com.nhpatt.hibernate.petition.Petition;
import com.nhpatt.hibernate.petition.PetitionTest;
import com.nhpatt.hibernate.user.User;

public class OwnerTest extends PetitionTest {

	@Test
	public void bidirectionalWithMappedByTest() {
		User user = saveUserWithAPetition();
		
		Session session = getSession();
		assertFalse(user.getPetitions().isEmpty());
		for (Petition petition : user.getPetitions()) {
			assertNotNull(petition.getUser());
		}
		session.close();
	}

	
}
