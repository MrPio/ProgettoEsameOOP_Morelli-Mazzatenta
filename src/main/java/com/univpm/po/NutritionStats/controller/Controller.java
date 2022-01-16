package com.univpm.po.NutritionStats.controller;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.DropboxAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.*;
import com.univpm.po.NutritionStats.exception.*;
import com.univpm.po.NutritionStats.model.*;
import com.univpm.po.NutritionStats.service.FilterManager;
import com.univpm.po.NutritionStats.service.MainService;
import com.univpm.po.NutritionStats.utility.Serialization;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 * <p>This is the {@code RestController}, the <strong>FIRST</strong> step of any client
 * request. It associates each supported request
 * with the correspondent {@code Service} method. It is the <i>bridge</i> between the
 * guest and this restful api. <strong>Here is where the checked exception thrown by
 * {@code Service} are handled </strong> with {@code try{}/catch{}}. All the
 * endpoint are memorized in {@code final} global variables below which are accessed
 * by the methods. These last returns instances of {@code ResponseEntity<Object>}
 * containing the {@code HttpStatus} and the {@code JsonObject} associated with the
 * response.
 *
 * <p><p>The <strong>{@code HttpStatus}</strong> is a number referring the type of
 * request result, here is a sample table:
 * <p>• 200 = <strong>OK</strong>: if everything went fine.
 * <p>• 400 = <strong>Bad Request</strong>: if some parameters are missing, or the
 * request body is incompatible with the request.
 * <p>• 404 = <strong>Not Found</strong>: when the requested endpoint is not
 * handled by the server
 * <p>• 405 = <strong>Method Not Allowed</strong>: the endpoint does not support
 * this request method, for example when using {@code GET} as request method on an
 * endpoint which supports only {@code POST}.
 * <p>• 500 = <strong>Internal Server Error</strong>: generic error generated due
 * to server internal error.
 * <p>• 501 = <strong>Not Implemented</strong>: when the server does not recognize
 * the request method.
 *
 * <p><p>The <strong>{@code JsonObject}</strong> contains a list of {@code key}-{@code value}.
 * They are the message returned to the client; depending on the requested endpoint it may
 * consist in just a notification about a potential error or the plain success, or it may
 * contain data about the user or his data, for example his <i><strong>Metadata</strong></i>,
 * ore some <i><strong>Statistics</strong></i>.
 *
 * <p>
 *
 * @author Morelli Valerio
 * @see com.univpm.po.NutritionStats.service.MainService MainService
 */
@RestController
public class Controller {
    final String ENDPOINT_FOOD = "/api/name/{food_name}";
    final String ENDPOINT_EAN = "/api/ean/{ean_code}";
    final String ENDPOINT_ADD_FOOD_BY_NAME = "/add/food/by_name";
    final String ENDPOINT_ADD_FOOD_BY_EAN = "/add/food/by_ean";
    final String ENDPOINT_ADD_WATER = "/add/water";
    final String ENDPOINT_DIARY_INFO = "/diary";
    final String ENDPOINT_DAY_INFO = "/diary/{day_id}";
    final String ENDPOINT_SIGNUP = "/signup";
    final String ENDPOINT_LOGIN = "/login";
    final String ENDPOINT_RESET = "/reset";
    final String ENDPOINT_UPDATE_WEIGHT = "/update_weight";
    final String ENDPOINT_STATS = "/stats";
    final String ENDPOINT_FILTERS = "/filters";

    MainService mainService = new MainService();

    /**
     * <strong>This endpoint is the main route</strong>
     *
     * @return new instance of {@code ResponseEntity} which contains a simple welcome message.
     */
    @RequestMapping(path = "/")
    public ResponseEntity<Object> Welcome() {
        return new ResponseEntity<>(
                new JSONObject(Map.of("welcome", "Welcome this is Nutrition Stats, RESTful API, " +
                        "you can find the repository here:\r\n" +
                        "https://github.com/MrPio/ProgettoEsameOOP_Morelli-Mazzatenta")),
                HttpStatus.OK);
    }

    /**
     * <p><strong>This endpoint provides a simple communication between the client and Edamam free api</strong>,
     * which has a huge database containing nutrition information about a lot of foods.
     * <p>As you can see in the {@link MainService} class, this endpoint does not always
     * lead to a request to Edamam api; this because {@link EdamamNutritionAnalysisAPI} uses
     * {@link Serialization} to store the response locally (and backups remotely on a dedicated dropbox
     * account using {@link DropboxAPI}) avoiding making the same request in the future. This <i>helps
     * a lot reducing the time needed</i> to fulfill the request of the client.
     *
     * <p><p><strong>{@code /api/name/{food_name} [GET]}</strong> route.
     *
     * @param foodName the name of the food to search
     * @param weight   the portion weight
     * @param measure  the unit of measure [GR, ML]
     * @return new instance of {@code ResponseEntity} which contains nutrition details about requested food name if found.
     * @see EdamamNutritionAnalysisAPI#getFood(String, int, Measure)
     * @see ApiFoodNotFoundException
     */
    @RequestMapping(path = ENDPOINT_FOOD, method = RequestMethod.GET)
    public ResponseEntity<Object> getInfoFromFoodName(
            @PathVariable("food_name") String foodName,
            @RequestParam(value = "portion_weight", defaultValue = "100") Integer weight,
            @RequestParam(value = "unit_of_measure", defaultValue = "GR") Measure measure) {
        try {
            return new ResponseEntity<>(
                    EdamamNutritionAnalysisAPI.getFood(foodName, weight, measure),
                    HttpStatus.OK);
        } catch (ApiFoodNotFoundException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(), "api", e.getApi())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint provides a simple communication between the client and Chomp api</strong>,
     * which has a huge database containing nutrition information about a lot of ean codes.
     *
     * <p>As you can see in the {@link MainService} class, this endpoint does not always
     * lead to a request to Edamam api; this because {@link ChompBarcodeSearchAPI} uses
     * {@link Serialization} to store the response locally (and backups remotely on a dedicated dropbox
     * account using {@link DropboxAPI}) avoiding making the same request in the future. This <i>helps
     * a lot reducing the time needed</i> to fulfill the request of the client. It is also useful to
     * reduce the calls to the api which has a strict limit.
     *
     * <p><p><strong>{@code /api/ean/{ean_code} [GET]}</strong> route.
     *
     * @param eanCode the ean code to search
     * @return new instance of {@code ResponseEntity} which contains nutrition details about requested ean code if found.
     * @see ChompBarcodeSearchAPI#getEanInfo(long)
     * @see ApiFoodNotFoundException
     * @see ChompLimitOvercameException
     */
    @RequestMapping(path = ENDPOINT_EAN, method = RequestMethod.GET)
    public ResponseEntity<Object> getInfoFromEan(
            @PathVariable("ean_code") Long eanCode) {
        try {
            return new ResponseEntity<>(
                    ChompBarcodeSearchAPI.getEanInfo(eanCode),
                    HttpStatus.OK);
        } catch (ApiFoodNotFoundException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(), "api", e.getApi())), HttpStatus.BAD_REQUEST);
        } catch (ChompLimitOvercameException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "api", e.getApi(), "limit", e.getLimit())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used to add a specified {@link Food food} to the list of consumed
     * foods owned by {@link User}</strong>
     *
     * <p>As you can see in the {@link User} class, <i>each user has a list of days where
     * a list of {@link Food food} are stored based on their belonging to {@link MealType meal types}.</i>
     *
     * <p><p><strong>{@code /add/food/by_name [POST]}</strong> route.
     *
     * @param token         the token used to identify the {@link User user}
     * @param dayId         the dayId of the {@link Day day} where to put {@link Food food}
     * @param mealType      the type of meal where to put the {@link Food food}
     * @param foodName      the name of the {@link Food food} to add
     * @param portionWeight the portion of the {@link Food food} to add
     * @param measureUnit   the unit of measure with which the portion value is given [GR, ML]
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestAddFoodByName(String, String, MealType, String, int, Measure)
     * @see ApiFoodNotFoundException
     * @see UserNotFound
     */
    @RequestMapping(path = ENDPOINT_ADD_FOOD_BY_NAME, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddFoodByName(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "meal_type") MealType mealType,
            @RequestParam(value = "food_name") String foodName,
            @RequestParam(value = "portion_weight") Integer portionWeight,
            @RequestParam(value = "unit_of_measure ", defaultValue = "GR") Measure measureUnit) throws NoSuchMethodException {
        try {
            return mainService.requestAddFoodByName(token, dayId, mealType, foodName, portionWeight, measureUnit);
        } catch (ApiFoodNotFoundException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(), "api", e.getApi())), HttpStatus.BAD_REQUEST);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used to add a {@link Food food} associated
     * to the provided ean code to the list of consumed foods owned by {@link User}</strong>
     *
     * <p>As you can see in the {@link User} class, <i>each user has a list of days where
     * a list of {@link Food food} are stored based on their belonging to {@link MealType meal types}.</i>
     *
     * <p><p><strong>{@code /add/food/by_ean [POST]}</strong> route.
     *
     * @param token         the token used to identify the {@link User user}
     * @param dayId         the dayId of the {@link Day day} where to put {@link Food food}
     * @param mealType      the type of meal where to put the {@link Food food}
     * @param eanCode       the ean code of the {@link Food food} to add
     * @param portionWeight the portion of the {@link Food food} to add
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestAddFoodByEan(String, String, MealType, long, int)
     * @see ApiFoodNotFoundException
     * @see UserNotFound
     * @see ChompLimitOvercameException
     */
    @RequestMapping(path = ENDPOINT_ADD_FOOD_BY_EAN, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddFoodByEan(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "meal_type") MealType mealType,
            @RequestParam(value = "ean_code") Long eanCode,
            @RequestParam(value = "portion_weight") Integer portionWeight) throws NoSuchMethodException {
        try {
            return mainService.requestAddFoodByEan(token, dayId, mealType, eanCode, portionWeight);
        } catch (ApiFoodNotFoundException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(), "api", e.getApi())), HttpStatus.BAD_REQUEST);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        } catch (ChompLimitOvercameException e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "api", e.getApi(), "limit", e.getLimit())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used to add a volume of {@link Water water}
     * to the list of drunk water owned by {@link User}</strong>
     *
     * <p>As you can see in the {@link User} class, <i>each user has a list of days where
     * a list of {@link Water water} are stored.</i>
     *
     * <p><p><strong>{@code /add/water [POST]}</strong> route.
     *
     * @param token  the token used to identify the {@link User user}
     * @param dayId  the dayId of the {@link Day day} where to put {@code Water}
     * @param volume the amount of water drunk
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestAddWater(String, String, int)
     * @see UserNotFound
     */
    @RequestMapping(path = ENDPOINT_ADD_WATER, method = RequestMethod.POST)
    public ResponseEntity<Object> requestAddWater(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "day_id") String dayId,
            @RequestParam(value = "portion_volume") Integer volume) throws NoSuchMethodException {
        try {
            return mainService.requestAddWater(token, dayId, volume);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used retrieve all the <i>metadata</i> owned by the {@link User}</strong>
     *
     * <p>These metadata comes from the <i>conversion (made by the framework) of the {@link Diary} object of
     * the {@link User} in a JsonObject</i>; each {@code public} getter has a corresponding {@code key} in
     * the response and its value is the {@code value} stored by the variable itself.
     *
     * <p><p><strong>{@code /diary [GET]}</strong> route.
     *
     * @param token the token used to identify the {@link User user}
     * @return new instance of {@code ResponseEntity} that provides all the data owned by the user.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestDiaryValues(String)
     * @see UserNotFound
     */
    @RequestMapping(path = ENDPOINT_DIARY_INFO, method = RequestMethod.GET)
    public ResponseEntity<Object> requestDiaryInfo(
            @RequestParam(value = "token") String token) throws NoSuchMethodException {
        try {
            return mainService.requestDiaryValues(token);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used retrieve all the <i>metadata</i> owned by the {@link User}
     * in a specified day</strong>
     *
     * <p>These metadata comes from the <i>conversion (made by the framework) of the {@link Day} object of
     * the {@link Diary} owned by the {@link User} in a JsonObject</i>; each {@code public} getter has
     * a corresponding {@code key} in the response and its {@code value} is the value stored by the variable itself.
     *
     * <p><p><strong>{@code /diary/{day_id} [GET]}</strong> route.
     *
     * @param token the token used to identify the {@link User user}
     * @param dayId the dayId of the requested {@link Day day}
     * @return new instance of {@code ResponseEntity} that provides all the data owned by the user in the specified day.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestDayValues(String, String)
     * @see UserNotFound
     */
    @RequestMapping(path = ENDPOINT_DAY_INFO, method = RequestMethod.GET)
    public ResponseEntity<Object> requestDayInfo(
            @PathVariable(value = "day_id") String dayId,
            @RequestParam(value = "token") String token) throws NoSuchMethodException {
        try {
            return mainService.requestDayValues(token, dayId);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used to sign up a new {@link User} </strong>
     *
     * <p>When a new {@link User} is registered a new {@link Diary} is created and serialized
     * locally (and remotely with {@link DropboxAPI}) in a file named as the token associated
     * to the new {@link User}.
     *
     * <p>The token is the encrypt value of the provided email with the <strong>md5</strong> hash function.
     *
     * <p>This operation fails if the provided email leads to an existing account, in this case
     * a {@link UserAlreadyInDatabase dedicated exception} is thrown by the {@link MainService service},
     * and the client is notified.
     *
     * <p><p><strong>{@code /signup [POST]}</strong> route.
     *
     * @param nickname the nickname of the new {@link User user}
     * @param email    the email of the new {@link User user} (used to generate the token)
     * @param birth    the birthdate of the new {@link User user}
     * @param weight   the weight of the new {@link User user}
     * @param height   the height of the new {@link User user}
     * @param diet     the diet plan of the new {@link User user}
     * @param gender   the gender of the new {@link User user}
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur or
     * the token associated to the new {@link User user} if everything went fine.
     * @see MainService#requestSignUp(User)
     * @see UserAlreadyInDatabase
     */
    @RequestMapping(path = ENDPOINT_SIGNUP, method = RequestMethod.POST)
    public ResponseEntity<Object> requestSignUp(
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "birth") String birth,
            @RequestParam(value = "weight") Integer weight,
            @RequestParam(value = "height") Integer height,
            @RequestParam("diet") Diet diet,
            @RequestParam(value = "gender") Gender gender) {
        try {
            return mainService.requestSignUp(new User(nickname, email, Diary.stringToLocalDate(birth), height, weight, diet, gender));
        } catch (UserAlreadyInDatabase e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "email", e.getEmail(), "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used to perform a login</strong>
     *
     * <p>The {@link MainService service} checks if the provided token can be found in the database;
     * if so, some basic data about the user are returned as response to the client.
     *
     * <p><p><strong>{@code /login [GET]}</strong> route.
     *
     * @param token the token used to identify the {@link User user}
     * @return new instance of {@code ResponseEntity} that provides a summary of the {@link User user}
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestLogin(String)
     * @see UserNotFound
     */
    @RequestMapping(path = ENDPOINT_LOGIN, method = RequestMethod.GET)
    public ResponseEntity<Object> requestLogin(
            @RequestParam(value = "token") String token) throws NoSuchMethodException {
        try {
            return mainService.requestLogin(token);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used to delete all the data owned by a {@link User}</strong>
     *
     * <p>This operation is irreversible, everything is deleted. The client may want to start from the beginning.
     *
     * <p><p><strong>{@code /reset [DELETE]}</strong> route.
     *
     * @param token the token used to identify the {@link User user}
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestReset(String)
     * @see UserNotFound
     */
    @RequestMapping(path = ENDPOINT_RESET, method = RequestMethod.DELETE)
    public ResponseEntity<Object> requestReset(
            @RequestParam(value = "token") String token) throws NoSuchMethodException {
        try {
            return mainService.requestReset(token);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used to register a new weight value</strong>
     *
     * <p>With this endpoint the {@link User user} can update his weight value.
     *
     * <p><p><strong>{@code /update_weight [PUT]}</strong> route.
     *
     * @param token  the token used to identify the {@link User user}
     * @param weight the new weight to register
     * @param date   the date of the measurement
     * @return new instance of {@code ResponseEntity} which contains a message about the error that might occur.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestUpdateWeight(String, float, LocalDate)
     * @see UserNotFound
     */
    @RequestMapping(path = ENDPOINT_UPDATE_WEIGHT, method = RequestMethod.PUT)
    public ResponseEntity<Object> requestUpdateWeight(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "weight") Float weight,
            @RequestParam(value = "date", defaultValue = "null") String date) throws NoSuchMethodException {
        try {
            LocalDate dateFormatted;
            if (date.equals("null"))
                dateFormatted = LocalDate.now();
            else
                dateFormatted = Diary.stringToLocalDate(date);
            return mainService.requestUpdateWeight(token, weight, dateFormatted);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used to generate statistics based on the filtered data owned by
     * the {@link User}</strong>
     *
     * <p>Here statistics like the <strong>sample mean</strong> and the <strong>sample variance</strong> are
     * calculated and provided to the client.
     *
     * <p>The client can also filter the data with which the statistics are being calculated with on
     * appropriate request json body:
     *
     * <p>• <strong>Start Date</strong>: the left period range extremity.
     * <p>• <strong>End Date</strong>: the right period range extremity.
     * <p>• <strong>Meal Type</strong>: the type of meals in which the client is interested.
     * <p>• <strong>Water</strong>: if the client want statistic on his water assumption.
     * <p>• <strong>Nutrient Name</strong>: a list of nutrients on which the statistics will be calculated
     *
     * <p><p><strong>{@code /stats [POST]}</strong> route.
     *
     * @param token         the token used to identify the {@link User user}
     * @param types         an array of {@code StatisticType} containing all the statistic in with the client is interested
     * @param filterManager the body of the request, containing all the parameters about the filter
     * @return new instance of {@code ResponseEntity} which contains all the values of all the statistics requested.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestStats(String, StatisticType[], ArrayList)
     * @see StatisticType
     * @see FilterManager
     * @see UserNotFound
     * @see EndDateBeforeStartDateException
     */
    @RequestMapping(path = ENDPOINT_STATS, method = RequestMethod.POST)
    public ResponseEntity<Object> requestStats(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "type") StatisticType[] types,
            @RequestBody FilterManager filterManager) throws NoSuchMethodException {
        try {
            if (filterManager.getException() != null && filterManager.getException().getClass() == EndDateBeforeStartDateException.class)
                throw (EndDateBeforeStartDateException) filterManager.getException();
            return mainService.requestStats(token, types, filterManager.getFiltersList());
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        } catch (EndDateBeforeStartDateException e) {
            return new ResponseEntity<>(new JSONObject(Map.of(
                    "message", e.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <p><strong>This endpoint is used retrieve filtered <i>metadata</i> owned by the {@link User}</strong>
     *
     * <p>The client can filter the data on
     * appropriate request json body:
     *
     * <p>• <strong>Start Date</strong>: the left period range extremity.
     * <p>• <strong>End Date</strong>: the right period range extremity.
     * <p>• <strong>Meal Type</strong>: the type of meals in which the client is interested.
     * <p>• <strong>Water</strong>: if the client want statistic on his water assumption.
     * <p>• <strong>Nutrient Name</strong>: a list of nutrients on which the statistics will be calculated
     *
     * <p><p><strong>{@code /filters [POST]}</strong> route.
     *
     * @param token         the token used to identify the {@link User user}.
     * @param filterManager the body of the request, containing all the parameters about the filter.
     * @return new instance of {@code ResponseEntity} which contains all the filtered data owned by the {@link User user}.
     * @throws NoSuchMethodException when the Java Reflect, which has no guarantee by the compiler that it will find that method, fails runtime.
     * @see MainService#requestFilters(String, ArrayList)
     * @see FilterManager
     * @see UserNotFound
     * @see EndDateBeforeStartDateException
     */
    @RequestMapping(path = ENDPOINT_FILTERS, method = RequestMethod.POST)
    public ResponseEntity<Object> requestFilter(
            @RequestParam(value = "token") String token,
            @RequestBody FilterManager filterManager) throws NoSuchMethodException {
        try {
            if (filterManager.getException() != null && filterManager.getException().getClass() == EndDateBeforeStartDateException.class)
                throw (EndDateBeforeStartDateException) filterManager.getException();
            return mainService.requestFilters(token, filterManager.getFiltersList());
        } catch (UserNotFound e) {
            return new ResponseEntity<>(new JSONObject(Map.of("message", e.getMessage(),
                    "token", e.getToken())), HttpStatus.BAD_REQUEST);
        } catch (EndDateBeforeStartDateException e) {
            return new ResponseEntity<>(new JSONObject(Map.of(
                    "message", e.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }
}
