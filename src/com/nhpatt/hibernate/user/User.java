package com.nhpatt.hibernate.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.nhpatt.hibernate.petition.Petition;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "userName")
	private String name;
	@Column(name = "surname", nullable = false)
	private String surname;
	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;

	@OneToMany
	@JoinColumn(name = "userId")
	private Set<Petition> petitions = new HashSet<Petition>();

	public Set<Petition> getPetitions() {
		return petitions;
	}

	public void setPetitions(Set<Petition> petitions) {
		this.petitions = petitions;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {
		super();
	}

	public User(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public User(String name, String surname, Role role) {
		this(name, surname);
		this.role = role;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public String getFullName() {
		return name + " " + surname;
	}

}
