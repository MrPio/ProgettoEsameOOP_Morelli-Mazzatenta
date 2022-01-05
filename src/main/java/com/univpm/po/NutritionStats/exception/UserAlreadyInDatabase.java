package com.univpm.po.NutritionStats.exception;

import com.univpm.po.NutritionStats.enums.Api;

public class UserAlreadyInDatabase extends Exception {
	final static String BASE_MESSAGE = "Sorry, but the email you entered is already used by another account";
	private String email;
	private String token;

	public UserAlreadyInDatabase(String email, String token) {
		super(BASE_MESSAGE);
		this.email = email;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}
}
