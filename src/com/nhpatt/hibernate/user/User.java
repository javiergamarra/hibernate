package com.nhpatt.hibernate.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
