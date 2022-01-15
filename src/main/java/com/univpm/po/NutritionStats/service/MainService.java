package com.univpm.po.NutritionStats.service;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.controller.Controller;
import com.univpm.po.NutritionStats.enums.*;
import com.univpm.po.NutritionStats.exception.*;
import com.univpm.po.NutritionStats.model.*;
import com.univpm.po.NutritionStats.service.filter.Filter;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> This is the {@code Service}, the <strong>SECOND</strong> step of any client
 * request. It is the brain behind the endpoint. As you can see, a lot of {@code generics}
 * are here used to simplify the appearance of the code, <strong>as a matter of fact  all
 * the methods below have pretty much the same code except for the method they are
 * referring to.</strong> In other words, most of them call the method
 * {@link MainService#workOnDiary(String, Method, Object...) workOnDiary}
 * passing an instance of {@link java.lang.reflect.Method Method} and an array
 * of {@link java.lang.Object objects} (the parameters of the to-be-called method)
 * as parameters. <i>A lot of recurring code has been rubbed out thanks to {@code generics}.</i>
 *
 * <p> Of course the usage of {@code generics} entails the chance for a
 * {@link java.lang.NoSuchMethodException NoSuchMethodException} to be thrown; but as
 * you can read in {@link com.univpm.po.NutritionStats.controller.Controller Controller}, this
 * exception may never be thrown since we know these methods exist, however, at the same time,
 * java {@code generics} have no guarantee by the compiler before runtime.
 *
 * <p>
 *
 * @author Morelli Valerio
 * @see Controller
 * @see MainService#workOnDiary(String, Method, Object...) MainService#workOnDiary
 */
@SuppressWarnings(value = "unchecked")
@Service
public class MainService {
    AllNutrientNonNutrient[] allNutrientNonNutrients = AllNutrientNonNutrient.values();

    /**
     * <strong>{@link Controller#requestAddFoodByName(String, String, MealType, String, Integer, Measure)
     * /add/food/by_name [POST]}</strong> route.
     *
     * @param token         the token used to identify the {@link User user}
     * @param dayId         the dayId of the {@link Day day} where to put {@link Food food}
     * @param mealType      the type of meal where to put the {@link Food food}
     * @param foodName      the name of the {@link Food food} to add
     * @param portionWeight the portion of the {@link Food food} to add
     * @param measureUnit   the unit of measure with which the portion value is given [GR, ML]
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException    when the Java Reflect, which has no guarantee by the compiler that it will
     *                                  find that method, fails runtime.
     * @throws UserNotFound             when the token doesn't belong to any registered {@link User user}.
     * @throws ApiFoodNotFoundException when the string {@code foodName} doesn't provide any result when submitted
     *                                  as request to {@link EdamamNutritionAnalysisAPI Edamam api}.
     * @see Controller#requestAddFoodByName(String, String, MealType, String, Integer, Measure) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestAddFoodByName(String token, String dayId, MealType mealType, String foodName,
                                                       int portionWeight, Measure measureUnit)
            throws NoSuchMethodException, UserNotFound, ApiFoodNotFoundException {
        return (ResponseEntity<Object>) workOnDiary(token,
                Diary.class.getMethod("addFood", String.class, MealType.class, Food.class), dayId, mealType,
                EdamamNutritionAnalysisAPI.getFood(foodName, portionWeight, measureUnit));
    }

    /**
     * <strong>{@link Controller#requestAddFoodByEan(String, String, MealType, Long, Integer)
     * /add/food/by_ean [POST]}</strong> route.
     *
     * @param token         the token used to identify the {@link User user}
     * @param dayId         the dayId of the {@link Day day} where to put {@link Food food}
     * @param mealType      the type of meal where to put the {@link Food food}
     * @param eanCode       the ean code of the {@link Food food} to add
     * @param portionWeight the portion of the {@link Food food} to add
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException       when the Java Reflect, which has no guarantee by the compiler that it will
     *                                     find that method, fails runtime.
     * @throws UserNotFound                when the token doesn't belong to any registered {@link User user}.
     * @throws ApiFoodNotFoundException    when the string {@code eanCode} doesn't provide any result when submitted
     *                                     as request to {@link ChompBarcodeSearchAPI Chomp api}.
     * @throws ChompLimitOvercameException due to strict api limitation, we cannot let a high number of requests to pass.
     * @see Controller#requestAddFoodByEan(String, String, MealType, Long, Integer) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestAddFoodByEan(String token, String dayId, MealType mealType, long eanCode,
                                                      int portionWeight)
            throws NoSuchMethodException, UserNotFound, ApiFoodNotFoundException, ChompLimitOvercameException {
        return (ResponseEntity<Object>) workOnDiary(token,
                Diary.class.getMethod("addFood", String.class, MealType.class, Food.class), dayId, mealType,
                ChompBarcodeSearchAPI.getFood(eanCode, portionWeight));
    }

    /**
     * <strong>{@link Controller#requestAddWater(String, String, Integer) /add/water [POST]}</strong> route.
     *
     * @param token  the token used to identify the {@link User user}
     * @param dayId  the dayId of the {@link Day day} where to put {@code Water}
     * @param volume the amount of water drunk
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @throws UserNotFound          when the token doesn't belong to any registered {@link User user}.
     * @see Controller#requestAddWater(String, String, Integer) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestAddWater(String token, String dayId, int volume)
            throws NoSuchMethodException, UserNotFound {
        return (ResponseEntity<Object>) workOnDiary(token, Diary.class.getMethod("addWater", String.class, Water.class),
                dayId, new Water(volume));

    }

    /**
     * <strong>{@link Controller#requestDiaryInfo(String) /diary [GET]}</strong> route.
     *
     * @param token the token used to identify the {@link User user}
     * @return new instance of {@code ResponseEntity} that provides all the data owned by the user.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @throws UserNotFound          when the token doesn't belong to any registered {@link User user}.
     * @see Controller#requestDiaryInfo(String) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestDiaryValues(String token)
            throws NoSuchMethodException, UserNotFound {
        JSONObject response = (JSONObject) workOnDiary(token, Diary.class.getMethod("toJsonObject"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * <strong>{@link Controller#requestDayInfo(String, String) /diary/{day_id} [GET]}</strong> route.
     *
     * @param token the token used to identify the {@link User user}
     * @param dayId the dayId of the requested {@link Day day}
     * @return new instance of {@code ResponseEntity} that provides all the data owned by the user in the specified day.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @throws UserNotFound          when the token doesn't belong to any registered {@link User user}.
     * @see Controller#requestDayInfo(String, String) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestDayValues(String token, String dayId)
            throws NoSuchMethodException, UserNotFound {
        Day requestedDay = (Day) workOnDiary(token, Diary.class.getMethod("findDayById", String.class), dayId);
        if (requestedDay != null)
            return new ResponseEntity<>(generateDaySummary(requestedDay), HttpStatus.OK);
        else
            return new ResponseEntity<>(new JSONObject(Map.of("result", "nothing to show on this day...")),
                    HttpStatus.OK);
    }

    /**
     * <strong>{@link Controller#requestSignUp(String, String, String, Integer, Integer, Diet, Gender)
     * /signup [POST]}</strong> route.
     *
     * @param user an instance of {@link User} containing all the information provided by the client through the request.
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur or
     * the token associated to the new {@link User user} if everything went fine.
     * @throws UserAlreadyInDatabase when the provided email leads to an existing account.
     * @see Controller#requestSignUp(String, String, String, Integer, Integer, Diet, Gender) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestSignUp(User user)
            throws UserAlreadyInDatabase {
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

    /**
     * <strong>{@link Controller#requestLogin(String) /login [GET]}</strong> route.
     *
     * @param token the token used to identify the {@link User user}
     * @return new instance of {@code ResponseEntity} that provides a summary of the {@link User user}
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @throws UserNotFound          when the token doesn't belong to any registered {@link User user}.
     * @see Controller#requestLogin(String) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestLogin(String token)
            throws NoSuchMethodException, UserNotFound {
        User response = (User) workOnDiary(token, Diary.class.getMethod("getUser"));
        return new ResponseEntity<>(new JSONObject(Map.of("result", "success!", "user", response)), HttpStatus.OK);
    }

    /**
     * <strong>{@link Controller#requestReset(String) /reset [DELETE]}</strong> route.
     *
     * @param token the token used to identify the {@link User user}
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @throws UserNotFound          when the token doesn't belong to any registered {@link User user}.
     * @see Controller#requestReset(String) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestReset(String token)
            throws NoSuchMethodException, UserNotFound {
        return (ResponseEntity<Object>) workOnDiary(token, Diary.class.getMethod("reset"));
    }

    /**
     * <strong>{@link Controller#requestUpdateWeight(String, Float, String) /update_weight [PUT]}</strong> route.
     *
     * @param token  the token used to identify the {@link User user}
     * @param weight the new weight to register
     * @param date   the date of the measurement
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @throws UserNotFound          when the token doesn't belong to any registered {@link User user}.
     * @see Controller#requestUpdateWeight(String, Float, String) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestUpdateWeight(String token, float weight, LocalDate date)
            throws NoSuchMethodException, UserNotFound {
        return (ResponseEntity<Object>) workOnDiary(token,
                Diary.class.getMethod("updateWeight", float.class, LocalDate.class), weight, date);
    }

    /**
     * <strong>{@link Controller#requestStats(String, StatisticType[], FilterManager) /stats [POST]}</strong> route.
     *
     * @param token   the token used to identify the {@link User user}
     * @param types   an array of {@code StatisticType} containing all the statistic in with the client is interested
     * @param filters the body of the request, containing all the parameters about the filters.
     * @return new instance of {@code ResponseEntity} which contains all the values of all the statistics requested.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @throws UserNotFound          when the token doesn't belong to any registered {@link User user}.
     * @see Controller#requestStats(String, StatisticType[], FilterManager) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestStats(String token, StatisticType[] types, ArrayList<Filter> filters)
            throws NoSuchMethodException, UserNotFound {
        JSONObject response = (JSONObject) workOnDiary(token, Diary.class.getMethod("doStatistic",
                ArrayList.class, StatisticType[].class), filters, types);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * <strong>{@link Controller#requestFilter(String, FilterManager)  /filters [POST]}</strong> route.
     *
     * @param token   the token used to identify the {@link User user}.
     * @param filters the body of the request, containing all the parameters about the filters.
     * @return new instance of {@code ResponseEntity} which contains all the filtered data owned by the {@link User user}.
     * @throws UserNotFound          when the token doesn't belong to any registered {@link User user}.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see Controller#requestFilter(String, FilterManager) for further details about how this endpoint works.
     */
    public ResponseEntity<Object> requestFilters(String token, ArrayList<Filter> filters)
            throws NoSuchMethodException, UserNotFound {
        Diary diary = (Diary) workOnDiary(token, Diary.class.getMethod("doFilter", ArrayList.class), filters);
        return new ResponseEntity<>(new JSONObject(Map.of("result", "success!", "diary", diary)), HttpStatus.OK);

    }

    /**
     * Returns a summary with basic information about the {@link User user} in the requested {@link Day day}.
     *
     * @param day the day when the information will be taken.
     * @return an {@link HashMap HashMap<String, Object>} containing the summary.
     */
    private HashMap<String, Object> generateDaySummary(Day day) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("day_id", day.calculateDayId());
        response.put("calories", day.getTotalCalories());
        for (AllNutrientNonNutrient nutrient : allNutrientNonNutrients)
            response.put(nutrient.getReferenceClass().getSimpleName().toLowerCase(),
                    day.calculate(nutrient.getReferenceClass()));
        response.put("water", day.calculate(Water.class));
        return response;
    }

    /**
     * This is the center of the class where all the methods redirect to. With the usage of generics repetitive code
     * is avoided.
     *
     * @param token   the token used to identify the {@link User user}.
     * @param method an instance of {@link Method} referring to the method to invoke.
     * @param param (varargs) an {@link java.lang.reflect.Array Array} of {@link Object objects} which representd
     * @return
     * @throws UserNotFound when the token doesn't belong to any registered {@link User user}.
     */
    private Object workOnDiary(String token, Method method, Object... param)
            throws UserNotFound {
        JSONObject response = new JSONObject();
        HttpStatus httpStatus;
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
            throw new UserNotFound(token);
        }
        return new ResponseEntity<>(new JSONObject(response), httpStatus);
    }
}