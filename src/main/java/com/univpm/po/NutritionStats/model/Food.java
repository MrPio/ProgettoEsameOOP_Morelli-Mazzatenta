package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.model.nutrient.NotNutrient;
import com.univpm.po.NutritionStats.model.nutrient.Nutrient;
import com.univpm.po.NutritionStats.enums.Diet;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.nutrient.*;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a generic food with a name, a portion weight, a unity of measure,
 * the type of diet it belongs to, a nutrient list and a not nutrient list
 * 
 * @author Davide
 *
 */
public class Food implements Serializable {
	private String name;
	private int portionWeight;
	private Measure measure;
	private Diet diet;
	private ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();
	private ArrayList<NotNutrient> notNutrientList = new ArrayList<NotNutrient>();

	/**
	 * Class constructor that instantiates a food with a name, a portion weight, a
	 * unity of measure and a type of diet
	 * 
	 * @param name          of food
	 * @param portionWeight of food
	 * @param measure       the unity of measure of the portion, for example gram
	 * @param diet          type of diet the food belongs to, for example salad
	 *                      belongs to vegetarian
	 */
	public Food(String name, int portionWeight, Measure measure, Diet diet) {
		this.name = name;
		this.portionWeight = portionWeight;
		this.measure = measure;
		this.diet = diet;
	}

	/**
	 * @return the food name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the food portion weight
	 */
	public int getPortionWeight() {
		return portionWeight;
	}

	/**
	 * @return the type of diet to which a food belongs
	 */
	public Diet getDiet() {
		return diet;
	}

	/**
	 * @return the nutrient list
	 */
	public ArrayList<Nutrient> getNutrientList() {
		return nutrientList;
	}

	/**
	 * @return the not nutrient list
	 */
	public ArrayList<NotNutrient> getNotNutrientList() {
		return notNutrientList;
	}

	/**
	 * @return the unity of measure attributed to a food
	 */
	public Measure getMeasure() {
		return measure;
	}

	/**
	 * add a nutrient to the list of nutrient
	 * 
	 * @param nutrient to add
	 */
	public void addNutrient(Nutrient nutrient) {
		nutrientList.add(nutrient);
	}

	/**
	 * add a not nutrient to the not nutrient list
	 * 
	 * @param notNutrient to add
	 */
	public void addNotNutrient(NotNutrient notNutrient) {
		notNutrientList.add(notNutrient);
	}

	/**
	 * Calculate the total calories through macronutrients in the nutrient list.
	 * Macronutrients are the only that have calories.
	 * 
	 * @return total calories of a food
	 */
	public int getTotalCalories() {
		float foodCalories = 0;
		for (Nutrient nutrient : nutrientList) {
			if (nutrient instanceof MacroNutrient)
				foodCalories += ((MacroNutrient) nutrient).calculateCalories();
		}
		return (int) foodCalories;
	}

	/**
	 * Changes the portion weight of a food with the one given by parameter. It also
	 * changes nutrients and not nutrients quantity by scaling them considering the
	 * new portion weight
	 * 
	 * @param newPortionWeight that replace the old one
	 */
	public void newPortionWeight(int newPortionWeight) {
		float scale = (float) newPortionWeight / portionWeight;
		portionWeight = newPortionWeight;
		for (var nutrient : nutrientList) {
			nutrient.setQuantity(nutrient.getQuantity() * scale);
			if (nutrient instanceof Carbohydrate)
				((Carbohydrate) nutrient)
						.setQuantityOnlySugar(((Carbohydrate) nutrient).getQuantityOnlySugar() * scale);
			else if (nutrient instanceof Lipid)
				((Lipid) nutrient).setQuantityOnlySaturated(((Lipid) nutrient).getQuantityOnlySaturated() * scale);
		}
		for (var notNutrient : notNutrientList)
			notNutrient.setQuantity(notNutrient.getQuantity() * scale);
	}

	/**
	 * Calculate the quantity of nutrient and not nutrient dependind on the class
	 * you give as a parameter. If the Nutrient class is the same or a superclass of
	 * the parameter, the method returns the quantity of a nutrient. If the
	 * NotNutrient class is the same or a superclass of the parameter, the method
	 * returns the quantity of a not nutrient. Else it returns 0.
	 * 
	 * @param <T>     means that the method will be dealing with generic type
	 * @param myClass is a class object of a specific class type <T>. It can be a
	 *                String, an Integer or a Object
	 * @return nutrient or not nutrient quantity in a food
	 */
	public <T> float calculate(Class<T> myClass) {
		if (Nutrient.class.isAssignableFrom(myClass)) {
			for (Nutrient nutrient : nutrientList)
				if (myClass.isInstance(nutrient))
					return nutrient.getQuantity();
		} else if (NotNutrient.class.isAssignableFrom(myClass)) {
			for (NotNutrient notNutrient : notNutrientList)
				if (myClass.isInstance(notNutrient))
					return notNutrient.getQuantity();
		}
		return 0.0f;
	}
}
