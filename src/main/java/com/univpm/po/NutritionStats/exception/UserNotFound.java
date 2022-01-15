package com.univpm.po.NutritionStats.exception;

/**
 * The {@code UserNotFound} class is an {@link Exception} thrown when a provided token doesn't load
 * to any existing user inside the local ore remote database.
 * @author Valerio Morelli
 */
public class UserNotFound extends Exception {
	final static String BASE_MESSAGE = "Sorry, but this token doesn't belong to any users, please retry.";
	private final String token;

	/**
	 * The constructor of the exception.
	 * @param token the token provided by the client which didn't lead to any existing user.
	 */
	public UserNotFound(String token) {
		super(BASE_MESSAGE);
		this.token = token;
	}

	/**
	 *
	 * @return the token provided by the client which didn't lead to any existing user.
	 */
	public String getToken() {
		return token;
	}
}
