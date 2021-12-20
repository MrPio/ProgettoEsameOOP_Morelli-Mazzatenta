package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.AllNutrientNonNutrient;
import com.univpm.po.NutritionStats.enums.MealType;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.exception.ApiFoodNotFoundException;
import com.univpm.po.NutritionStats.exception.EndDateBeforeStartDateException;
import com.univpm.po.NutritionStats.exception.UserAlreadyInDatabase;
import com.univpm.po.NutritionStats.exception.UserNotFound;
import com.univpm.po.NutritionStats.model.*;
import com.univpm.po.NutritionStats.model.nutrient.*;
import com.univpm.po.NutritionStats.service.filter.FilterByDate;
import com.univpm.po.NutritionStats.service.statistic.Mean;
import com.univpm.po.NutritionStats.service.statistic.Percentage;
import com.univpm.po.NutritionStats.service.statistic.StandardDeviatiton;
import com.univpm.po.NutritionStats.service.statistic.Statistic;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainService {
	AllNutrientNonNutrient[] allNutrientNonNutrients = AllNutrientNonNutrient.values();

	public ResponseEntity<Object> requestAddFoodByName(String token, String dayId, MealType mealType, String foodName,
			int portionWeight, Measure measureUnit)
			throws ApiFoodNotFoundException, NoSuchMethodException, UserNotFound {
		return (ResponseEntity<Object>) workOnDiary(token,
				Diary.class.getMethod("addFood", String.class, MealType.class, Food.class), dayId, mealType,
				EdamamNutritionAnalysisAPI.getFood(foodName, portionWeight, measureUnit));
	}

	public ResponseEntity<Object> requestAddFoodByEan(String token, String dayId, MealType mealType, long eanCode,
			int portionWeight) throws ApiFoodNotFoundException, NoSuchMethodException, UserNotFound {
		return (ResponseEntity<Object>) workOnDiary(token,
				Diary.class.getMethod("addFood", String.class, MealType.class, Food.class), dayId, mealType,
				ChompBarcodeSearchAPI.getFood(eanCode, portionWeight));
	}

	public ResponseEntity<Object> requestAddWater(String token, String dayId, int volume)
			throws NoSuchMethodException, UserNotFound {
		return (ResponseEntity<Object>) workOnDiary(token, Diary.class.getMethod("addWater", String.class, Water.class),
				dayId, new Water(volume));

	}

	public ResponseEntity<Object> requestDiaryValues(String token) throws NoSuchMethodException, UserNotFound {
		JSONObject response = (JSONObject) workOnDiary(token, Diary.class.getMethod("toJsonObject"));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<Object> requestDayValues(String token, String dayId)
			throws NoSuchMethodException, UserNotFound {
		Day requestedDay = (Day) workOnDiary(token, Diary.class.getMethod("findDayById", String.class), dayId);
		if (requestedDay != null)
			return new ResponseEntity<>(dayToJsonObject(requestedDay), HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject(Map.of("result", "nothing to show on this day...")),
					HttpStatus.OK);
	}

	public ResponseEntity<Object> requestSignUp(User user) throws UserAlreadyInDatabase {
		HashMap<String, String> response = new HashMap<>();
		// check if User is already in database
		if (Diary.load(user.generateToken()) != null)
			throw new UserAlreadyInDatabase(user.getEmail(), user.generateToken());
		response.put("result", "user successfully registered! Please store you token to gain future access.");
		response.put("email", user.getEmail());
		response.put("token", user.generateToken());
		// store the new user in local and remote database
		Diary newDiary = new Diary(user);
		newDiary.save();
		return new ResponseEntity<>(new JSONObject(response), HttpStatus.OK);
	}

	public ResponseEntity<Object> requestLogin(String token) throws UserNotFound, NoSuchMethodException {
		User response = (User) workOnDiary(token, Diary.class.getMethod("getUser"));
		return new ResponseEntity<>(new JSONObject(Map.of("result", "success!", "user", response)), HttpStatus.OK);
	}

	public ResponseEntity<Object> requestUpdateWeight(String token, float weight, LocalDate date)
			throws NoSuchMethodException, UserNotFound {
		return (ResponseEntity<Object>) workOnDiary(token,
				Diary.class.getMethod("updateWeight", float.class, LocalDate.class), weight, date);
	}

	public ResponseEntity<Object> requestStats(String token, String type, LocalDate startDate, LocalDate endDate)
			throws UserNotFound, EndDateBeforeStartDateException {
		return (ResponseEntity<Object>) workOnStatistic(token, type, startDate, endDate);
	}
	
	private HashMap<String, Object> dayToJsonObject(Day day) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("day_id", day.calculateDayId());
		response.put("calories", day.calculateCalories());
		for (AllNutrientNonNutrient nutrient : allNutrientNonNutrients)
			response.put(nutrient.getReferenceClass().getSimpleName().toLowerCase(),
					day.calculate(nutrient.getReferenceClass()));
		response.put("water", day.calculate(Water.class));
		return response;
	}

	private Object workOnDiary(String token, Method method, Object... param) throws UserNotFound {
		Object obj = null;
		LocalDate obj1 = (LocalDate) obj;

		JSONObject response = new JSONObject();
		HttpStatus httpStatus = null;
		Diary requestedDiary = Diary.load(token);
		if (requestedDiary != null) {
			try {
				if (method.getReturnType() == void.class)
					method.invoke(requestedDiary, param);
				else
					return method.invoke(requestedDiary, param);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			response.put("result", "success!");
			httpStatus = HttpStatus.OK;
		} else {
			response.put("result", "diary not found");
			httpStatus = HttpStatus.BAD_REQUEST;
			throw new UserNotFound(token);
		}
		return new ResponseEntity<>(new JSONObject(response), httpStatus);
	}

	private Object workOnStatistic(String token, String method, LocalDate startDate, LocalDate endDate) throws EndDateBeforeStartDateException, UserNotFound {
		JSONObject response = new JSONObject();
		HttpStatus httpStatus = null;
		Diary requestedDiary = Diary.load(token);
		
		if (requestedDiary != null) {
			switch (method) {
			case ("percentage"):
				Percentage percentage = new Percentage(requestedDiary);
			    response.putAll(percentage.macroNutrientPercentage(startDate, endDate));
				httpStatus = HttpStatus.OK;
				break;
			case ("mean"):
				Mean mean = new Mean(requestedDiary);
				response.putAll(mean.calculateMean(startDate, endDate));
				httpStatus = HttpStatus.OK;
				break;
			case ("standard deviation"):
				StandardDeviatiton standardDeviation = new StandardDeviatiton(requestedDiary);
				response.putAll(standardDeviation.calculateStandardDeviation(startDate, endDate));
				httpStatus = HttpStatus.OK;
				break;
			default:
				response.put("result", "Method not found");
				httpStatus = HttpStatus.BAD_REQUEST;
				break;
			}
		} else {
			response.put("result", "diary not found");
			httpStatus = HttpStatus.BAD_REQUEST;
			throw new UserNotFound(token);
		}
		return new ResponseEntity<>(new JSONObject(response), httpStatus);
	}
	
}