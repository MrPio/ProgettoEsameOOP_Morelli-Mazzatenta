package com.univpm.po.NutritionStats.exception;

/**
 * The {@code UserAlreadyInDatabase} class is an {@link Exception} thrown when a signup request is made using
 * an already registered email.
 *
 * @author Valerio Morelli
 */
public class UserAlreadyInDatabase extends Exception {
    final static String BASE_MESSAGE = "Sorry, but the email you entered is already used by another account";
    private String email;
    private String token;

    /**
     * The constructor of the exception.
     *
     * @param email the already registered email
     * @param token the token related to the email above
     */
    public UserAlreadyInDatabase(String email, String token) {
        super(BASE_MESSAGE);
        this.email = email;
        this.token = token;
    }

    /**
     * @return the existing email with which the client tried to sign up.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the token related to the email
     */
    public String getToken() {
        return token;
    }
}
