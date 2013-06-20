package com.nhpatt.hibernate.hellodatabase;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	public Test() {

	}

	public Test(final Integer id, final String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}

}
