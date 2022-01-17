package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Represents a message with a text, the time it has appeared and and whether it
 * has been read or not.
 *
 * @author Valerio Morelli
 *
 */
public class Message implements Serializable {
    private final String message;
    private final LocalTime time;
    private boolean read;

	/**
	 * Class first constructor that instantiates a message with a text, time(now) and
	 * which has not been read.
	 * 
	 * @param message the text of the message
	 */
	public Message(String message) {
		this.message = message;
		this.time = LocalTime.now();
		read = false;
	}

	/**
	 * Class second constructor that instantiates a message with a text, a time and
	 * which has not been read.
	 * 
	 * @param message the text of the message
	 * @param time    of the message
	 */
	public Message(String message, LocalTime time) {
		this.message = message;
		this.time = time;
		read = false;
	}

	/**
	 * @return the text of the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the time of the message
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Mark the message is read
	 */
	public void markAsRead() {
		read = true;
	}
}
