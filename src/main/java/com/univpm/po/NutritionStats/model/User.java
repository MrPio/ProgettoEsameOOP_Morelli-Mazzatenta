package com.univpm.po.NutritionStats.model;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashMap;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Diet{CLASSIC,PESCATARIAN,VEGETARIAN,VEGAN};
    public enum Gender{MALE,FEMALE};

    private String nickname;
    private String email;
    private Diet diet;
    private Gender gender;
    private int yearOfBirth;
    private HashMap<LocalDate, Integer> height = new HashMap<>();
    private HashMap<LocalDate, Float> weight = new HashMap<>();
    //private boolean celiac;

    public User(String email) {
        this.email = email;
    }

    public User(String nickname, String email, int yearOfBirth,
                int height, float weight, Diet diet,Gender gender) {
        this.nickname = nickname;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.height.put(LocalDate.now(),height);
        this.weight.put(LocalDate.now(),weight);
        this.diet=diet;
        this.gender=gender;
    }

    public String getNickname() {
        return nickname;
    }
    public String getEmail() {
        return email;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public HashMap<LocalDate, Integer> getHeight() {
        return height;
    }
    public HashMap<LocalDate, Float> getWeight() {
        return weight;
    }
    public Diet getDiet() {
        return diet;
    }
    public Gender getGender() {
        return gender;
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
}
