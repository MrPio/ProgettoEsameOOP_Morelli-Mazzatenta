package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Gender;
import org.apache.tomcat.jni.Local;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class User implements Serializable {

	private String nickname;
	private String email;
	private Diet diet;
	private Gender gender;
	private LocalDate birth;
	private int height;
	private TreeMap<LocalDate, Float> weight = new TreeMap<LocalDate, Float>();
	private int dailyCaloricIntake;

	public User(String email) {
		this.email = email;
	}

	public User(String nickname, String email, LocalDate birth, int height, float weight, Diet diet, Gender gender) {
		this.nickname = nickname;
		this.email = email;
		this.birth = birth;
		this.height = height;
		this.weight.put(LocalDate.now(), weight);
		this.diet = diet;
		this.gender = gender;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getYearOfBirth() {
		return birth;
	}

	public int getHeight() {
		return height;
	}

	public TreeMap<LocalDate, Float> getWeight() {
		return weight;
	}

	public Diet getDiet() {
		return diet;
	}

	public Gender getGender() {
		return gender;
	}

	public int getDailyCaloricIntake() {
		return dailyCaloricIntake;
	}

	public String generateToken() {
		byte[] bytesOfMessage = new byte[0];
		try {
			bytesOfMessage = email.getBytes(StandardCharsets.UTF_8);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] theMD5digest = md.digest(bytesOfMessage);
			StringBuffer sb = new StringBuffer();
			for (byte b : theMD5digest) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int calculateCaloricIntake() {

		LocalDate lastDate = weight.lastKey();
		Period age = Period.between(birth, LocalDate.now());
		if (gender == Gender.MALE)
			return (int) (((10 * weight.get(lastDate)) + (6.25 * height) - (5 * age.getYears()) + 5) * 1.4);
		else
			return (int) (((10 * weight.get(lastDate)) + (6.25 * height) - (5 * age.getYears()) - 161) * 1.4);
	}

}
