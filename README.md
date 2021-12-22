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
Type | Route | Params | Body | Description
---- | ---- | ---- | ---- | ----  
🔵**POST** | `/signup` | **`nickname`, `email`, `birth`, `height`, `weight`, `diet`, `gender`** | -| Register new user.
🔵**POST** | `/add/food/by_name` | **`token`, `day_id`, `meal_type`, `food_name`, `portion_weight`, `unit_of_measure`** |- | Add consumed food to a specified meal by its name.
🔵**POST** | `/add/food/by_ean` | **`token`, `day_id`, `meal_type`, `ean_code`, `portion_weight`** |- | Add consumed food to a specified meal by its name.
🔵**POST** | `/add/water` | **`token`, `day_id`, `portion_volume`** |- | Add consumed water to a specified meal.
🔴**DELETE** | `/reset` | **`token`** |- | Deletes all data owned by the user.``
🟡**PUT** | `/update_weight` | **`token`, `weight`** |- | Update the value of user's weight.
🟢**GET** | `/api/name/{food_name}` | **`portion_weight`, `unit_of_measure`** | -| Return information about food by its name.
🟢**GET** | `/api/ean/{ean_code}` | - |- | Return information about product by its ean code.
🟢**GET** | `/login` | **`token`**|-  | Login user, return user's info if in the database.
🟢**GET** | `/diary` | **`token`** |- | Return all the data (`metadata`) owned by the user.
🟢**GET** | `/diary/{day_id}` | **`token`** | -| Return the data of the user in specified day.
🟢**GET** | `/stats` | **`token`** |- | Get all the stats for the current user, you can filter the response by days range and by witch stats you need.
🟢**GET** | `/filters` | **`token`** |- | Filter metadata
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
## Application UML ☀:
### •🔰 Model:
![Model UML](graphics/NutritionStats-UML.jpg)
