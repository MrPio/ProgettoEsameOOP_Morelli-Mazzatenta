# NutritionStats
Project made for the Object-Oriented Programming course a.a. 2021/2022

# Description of the Project

### *Remote Access*

>Remote access to this rest API: (***now deployed***):
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
🔵**POST** | `/signup` | `nickname`, `email`, `birth`, `height`, `weight`, `diet`, `gender` | -| Register new user.
🔵**POST** | `/add/food/by_name` | `token`, `day_id`, `meal_type`, `food_name`, `portion_weight`, `unit_of_measure` |- | Add consumed food to a specified meal by its name.
🔵**POST** | `/add/food/by_ean` | `token`, `day_id`, `meal_type`, `ean_code`, `portion_weight` |- | Add consumed food to a specified meal by its name.
🔵**POST** | `/add/water` | `token`, `day_id`, `portion_volume` |- | Add consumed water to a specified meal.
🔵**POST** | `/stats` | `token` | `start_date`, `end_date`, `meal_type`, `food_name`, `nutrient_name[]` | Get all the stats for the current user, you can filter the response by days range and by witch stats you need.
🔵**POST** | `/filters` | - | `start_date`, `end_date`, `meal_type`, `food_name`, `nutrient_name[]` | Filter metadata by what you need.
🔴**DELETE** | `/reset` | `token` |- | Deletes all data owned by the user.``
🟡**PUT** | `/update_weight` | `token`, `weight` |- | Update the value of user's weight.
🟢**GET** | `/api/name/{food_name}` | `portion_weight`, `unit_of_measure` | -| Return information about food by its name.
🟢**GET** | `/api/ean/{ean_code}` | - |- | Return information about product by its ean code.
🟢**GET** | [`/login`](https://nutritionstatsoop.herokuapp.com/login?token=3959de8aeefabfa1385135fa8d03ee21) | `token`|-  | Login user, return user's info if in the database.
🟢**GET** | [`/diary`](https://nutritionstatsoop.herokuapp.com/diary?token=3959de8aeefabfa1385135fa8d03ee21) | `token` |- | Return all the data (`metadata`) owned by the user.
🟢**GET** | [`/diary/{day_id}`](https://nutritionstatsoop.herokuapp.com/diary/23-12-2021?token=3959de8aeefabfa1385135fa8d03ee21) | `token` | -| Return the data of the user in specified day.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
## Application UML ☀:
### •🔰 Packages UML:

### •🔰 model UML:
![model UML](graphics/model.jpg)

### •🔰 nutrient UML:
![nutrient UML](graphics/nutrient.jpg)

### •🔰 exception UML:
![exception UML](graphics/exception.jpg)

### •🔰 enum UML:
![enum UML](graphics/enum.jpg)

### •🔰 filter UML:
![filter UML](graphics/filter.jpg)

## Tools 
- ###### Software:

  [Eclipse](https://www.eclipse.org/) - java IDE

  [InteelliJ IDEA](https://www.jetbrains.com/idea/) - java IDE

  [SpringBoot](https://spring.io/projects/spring-boot) - open source Java-based framework used to create a micro Service.

  [PostMan](https://www.postman.com) - API platform for building and using APIs.

  [Maven](https://maven.apache.org/) - Software project management and comprehension tool based on the concept of project object model (POM).
  
  [Draw.io](https://app.diagrams.net/) - Diagram software used for UML

- ###### External REST API:

  [Edamam](https://developer.edamam.com/) - First food and nutrition database API.

  [Chomp](https://chompthis.com/api/) - Second food and nutrition database API.
