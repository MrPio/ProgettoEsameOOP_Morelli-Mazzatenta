package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Gender;
import com.univpm.po.NutritionStats.utility.InputOutput;
import com.univpm.po.NutritionStats.utility.Serialization;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.TreeMap;

/**
 * Represents the program user with his features like nickname and email needed
 * to register and personal informations useful for the purposes of the program.
 *
 * @author Davide Mazzatenta
 */
public class User implements Serializable {

    private String nickname;
    private String email;
    private Diet diet;
    private Gender gender;
    private LocalDate birth;
    private int height;
    private TreeMap<LocalDate, Float> weight;
    private Mailbox mailBox;

    /**
     * Class first constructor that instantiates an user with only his email and
     * empty maps of weights and mails.
     *
     * @param email
     */
    public User(String email) {
        this.email = email;
        weight = new TreeMap<>();
        mailBox = new Mailbox();
    }

    /**
     * Class second constructor that instantiates an user with nickname, email,
     * birth date, height, weight, the type of diet he wants to follow and the
     * gender
     *
     * @param nickname
     * @param email
     * @param birth
     * @param height
     * @param weight
     * @param diet
     * @param gender
     */
    public User(String nickname, String email, LocalDate birth, int height, float weight, Diet diet, Gender gender) {
        this.nickname = nickname;
        this.email = email;
        this.birth = birth;
        this.height = height;
        this.weight = new TreeMap<>() {
            {
                put(LocalDate.now(), weight);
            }
        };
        this.diet = diet;
        this.gender = gender;
        this.mailBox = new Mailbox();
    }

    /**
     * @return the user's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the user's date of birth
     */
    public LocalDate getYearOfBirth() {
        return birth;
    }

    /**
     * @return the user's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the user's weight and date associated with it
     */
    public TreeMap<LocalDate, Float> getWeight() {
        return weight;
    }

    /**
     * set the user's weight and date associated
     *
     * @param weight to set
     */
    public void setWeight(TreeMap<LocalDate, Float> weight) {
        this.weight = weight;
    }

    /**
     * @return the user's type of diet
     */
    public Diet getDiet() {
        return diet;
    }

    /**
     * @return the user's gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Calculate the daily caloric intake of the user based on his personal
     * informations like age, gender, height and weight
     *
     * @return the user's daily caloric intake
     */
    public int getDailyCaloricIntake() {
        LocalDate lastDate = weight.lastKey();
        Period age = Period.between(birth, LocalDate.now());
        if (gender == Gender.MALE)
            return (int) (((10 * weight.get(lastDate)) + (6.25 * height) - (5 * age.getYears()) + 5) * 1.4);
        else
            return (int) (((10 * weight.get(lastDate)) + (6.25 * height) - (5 * age.getYears()) - 161) * 1.4);
    }

    /**
     * Generate user's token that uniquely identifies him. It uses the cryptographic
     * hash function called MD5
     *
     * @return the user's token
     */
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

    /**
     * @return the mailBox containing all the read and unread messages of the user.
     */
    public Mailbox getMailBox() {
        loadMailbox();
        return mailBox;
    }

    public void loadMailbox() {
        InputOutput localDatabase = new InputOutput(Mailbox.DIR, generateToken() + ".dat");
        if (localDatabase.existFile())
            mailBox = (Mailbox) new Serialization(Mailbox.DIR, generateToken() + ".dat").loadObject();
        else
            mailBox = new Mailbox();
    }
}
