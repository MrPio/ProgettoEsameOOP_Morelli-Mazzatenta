package com.univpm.po.NutritionStats.Model;
import java.time.LocalDate;
import java.util.HashMap;

public class User {
	
	private String name;
	private String surname;
	private String nickname;
	private String email;
	private String password;
	private int yearOfBirth;
	private HashMap <LocalDate,String> height = new HashMap<LocalDate,String>();
	private HashMap <LocalDate,String> weight = new HashMap<LocalDate,String>();
	private boolean vegetarian;
	private boolean vegan;
	private boolean celiac;
	
	public User(String name, String surname, String nickname, String email, String password, int yearOfBirth,
			HashMap<LocalDate, String> height, HashMap<LocalDate, String> weight, boolean vegetarian, boolean vegan,
			boolean celiac) {
		this.name = name;
		this.surname = surname;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.yearOfBirth = yearOfBirth;
		this.height = height;
		this.weight = weight;
		this.vegetarian = vegetarian;
		this.vegan = vegan;
		this.celiac = celiac;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public HashMap<LocalDate, String> getHeight() {
		return height;
	}

	public void setHeight(HashMap<LocalDate, String> height) {
		this.height = height;
	}

	public HashMap<LocalDate, String> getWeight() {
		return weight;
	}

	public void setWeight(HashMap<LocalDate, String> weight) {
		this.weight = weight;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public boolean isVegan() {
		return vegan;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	public boolean isCeliac() {
		return celiac;
	}

	public void setCeliac(boolean celiac) {
		this.celiac = celiac;
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	
	
	
}
