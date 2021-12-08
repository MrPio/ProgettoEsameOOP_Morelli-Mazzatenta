package com.univpm.po.NutritionStats.model;

public class TestPerson {
	private String name;
	private String surname;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public TestPerson(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
}
