# NutritionStats
Project made for the Object-Oriented Programming course a.a. 2021/2022

### *Remote Access*

>Remote access to this rest API: (***still not deployed***):
>
> *https://nutritionstatsoop.herokuapp.com/*

### *Android application (client)*

>You can find here the client frontend made for this API (***Android application***):
>
> *https://github.com/MrPio/NutritionStats_client*
----------------------------------------------------------------------------------------------------------------------------------------

## Application Route 🌎:
Type | Route | Params | Description
---- | ---- | ---- | ----  
GET | `/api/ean/{ean_code}` |  | Return information about product by its ean code.
GET | */api/name/{food_name}* |  | Return information about food by its name.
POST | `/add/food` | token, day_id, meal_type, food_name, ean_code| Add consumed food to a specified meal.
POST | */add/meal* | token, day_id| Add an entire meal (list of foods).
GET | */diary* | token | Return all the data owned by the user.
GET | `/diary/{day_id}` | token | Return the data of the user in specified day.
POST | `/signup` | nickname, email, year, height, weight, diet, gender | Register new user.
GET | `/login` | token | Login user, return user's info if in the database.
GET | */stats* | | Get all the stats for the current user, you can filter the response by days range and by witch stats you need.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
## Application UML ☀:
### • Model:
![Model UML](graphics/NutritionStats-UML.jpg)
