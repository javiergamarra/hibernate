package com.nhpatt.hibernate.expediente;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Historial implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "historial", insertable = false, updatable = false)
	private Set<Expediente> expedientes = new HashSet<Expediente>();

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Set<Expediente> getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(final Set<Expediente> expedientes) {
		this.expedientes = expedientes;
	}

}