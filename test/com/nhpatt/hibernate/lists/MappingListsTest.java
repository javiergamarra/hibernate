package com.nhpatt.hibernate.lists;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.junit.Test;

import com.nhpatt.hibernate.petition.BankAccount;
import com.nhpatt.hibernate.petition.Petition;
import com.nhpatt.hibernate.utils.HibernateTest;

public class MappingListsTest extends HibernateTest {

	@Test
	public void mappingAListWithOrderColumnTest() {
		Session session = getSession();
		session.beginTransaction();

		Petition petition = new Petition();
		BankAccount bankAccount = new BankAccount();
		bankAccount.setPetition(petition);
		petition.getBankAccounts().add(bankAccount);

		BankAccount domesticAccount = new BankAccount();
		domesticAccount.setPetition(petition);
		petition.getBankAccounts().add(domesticAccount);

		session.save(petition);

		session.getTransaction().commit();
		session.close();

		session = getSession();

		Petition petitionFromBD = (Petition) session.get(Petition.class,
				petition.getId());
		assertEquals(bankAccount.getId(),
				petitionFromBD.getBankAccounts().get(0).getId());
		assertEquals(domesticAccount.getId(), petitionFromBD.getBankAccounts()
				.get(1).getId());

		session.close();

	}

}
