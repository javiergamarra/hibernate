package com.nhpatt.hibernate.expediente;

import junit.framework.Assert;

import org.hibernate.Session;
import org.junit.Test;

import com.nhpatt.hibernate.utils.HibernateTest;

public class ExpedienteTest extends HibernateTest {

	@Test
	public void insertarHistorialConExpedientes() {
		Session session = getSession();
		session.beginTransaction();

		Historial historial = new Historial();
		Expediente expediente = new Expediente();
		expediente.setHistorial(historial);
		historial.getExpedientes().add(expediente);
		session.save(historial);

		session.getTransaction().commit();
		session.close();

		session = getSession();
		Historial historialRecuperado = (Historial) session.get(
				Historial.class, historial.getId());
		Assert.assertFalse(historialRecuperado.getExpedientes().isEmpty());

	}

	@Test
	public void insertarExpedienteConHistorialSinCruce() {
		Session session = getSession();
		session.beginTransaction();

		Historial historial = new Historial();
		Expediente expediente = new Expediente();
		expediente.setHistorial(historial);
		session.save(expediente);

		session.getTransaction().commit();
		session.close();

		session = getSession();
		Historial historialRecuperado = (Historial) session.get(
				Historial.class, historial.getId());
		Assert.assertFalse(historialRecuperado.getExpedientes().isEmpty());

	}

	@Test
	public void insertarHistorialConExpedientesSinCruce() {
		Session session = getSession();
		session.beginTransaction();

		Historial historial = new Historial();
		Expediente expediente = new Expediente();
		// expediente.setHistorial(historial);
		historial.getExpedientes().add(expediente);
		session.save(historial);

		session.getTransaction().commit();
		session.close();

		session = getSession();
		Historial historialRecuperado = (Historial) session.get(
				Historial.class, historial.getId());
		Assert.assertFalse(historialRecuperado.getExpedientes().isEmpty());

	}

}