package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.utility.Serialization;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Represents a mail box with a map of Local Date associated with a message
 * list.
 * 
 * @author Valerio Morelli
 *
 */
public class Mailbox implements Serializable {
	public final static String DIR = "mailbox/";
	public final static String DROPBOX_DIR = "/mailbox/";

	TreeMap<LocalDate, ArrayList<Message>> mailbox;

	/**
	 * Class constructor that instantiates a mail box with a new map of Local Date
	 * associated with a message list.
	 */
	public Mailbox() {
		this.mailbox = new TreeMap<>();
	}

	/**
	 * @return the mail box map
	 */
	public TreeMap<LocalDate, ArrayList<Message>> getMailbox() {
		return mailbox;
	}

	/**
	 * Puts a message in the mail box if it contains the current time key. If not,
	 * it inserts the current time and add the respective message.
	 * 
	 * @param message to insert in the mail box
	 * @return mail box with new message
	 */
	public Mailbox sendMessage(Message message) {
		if (mailbox.containsKey(LocalDate.now()))
			mailbox.get(LocalDate.now()).add(message);
		else
			mailbox.put(LocalDate.now(), new ArrayList<>() {
				{
					add(message);
				}
			});
		return this;
	}

	/**
	 * Puts a message in the mail box in a specific date given as a parameter. If
	 * the mail box already contains date it simply inserts the message to that
	 * date. If not, it inserts date and add the respective message.
	 * 
	 * @param message to insert in the mail box
	 * @param date    in which insert message
	 * @return mail box with new message
	 */
	public Mailbox sendMessage(Message message, LocalDate date) {
		if (mailbox.containsKey(date))
			mailbox.get(date).add(message);
		else
			mailbox.put(date, new ArrayList<>() {
				{
					add(message);
				}
			});
		return this;
	}

	/**
	 * Saves the user's mail box through token to local database in mail box
	 * directory "mailbox/".
	 * 
	 * @param token user's token
	 */
	public void save(String token) {
		Serialization s = new Serialization(DIR, token + ".dat");
		s.saveObject(this);
	}
}
