
<div align="center">
<img alt="Logo" height="200" src="graphics/NutritionStatsLogo.jpg" width="200" />
 <div align="left">
   
# **NutritionStats**
Project made for the Object-Oriented Programming course a.a. 2021/2022


***

## 📘 Index 📘

* [Access](#access)
* [Description](#description)
* [Endpoints](#endpoints)
* [Uml](#uml)
* [Used tools](#tools)
* [Authors](#authors)

***

<a name="access"></a>
## 💻 Access💻
### *Remote Access*

>Remote access to this rest API: (***now deployed***):
>
> *https://nutritionstatsoop.herokuapp.com/*
----------------------------------------------------------------------------------------------------------------------------------------

### *Android application (client)*

>You can find here the client frontend made for this API (***Android application***):
>
> *https://github.com/MrPio/NutritionStats_client*
----------------------------------------------------------------------------------------------------------------------------------------

***

<a name="description"></a>
## 📋 Description of the Project 📋
NutritionStats offer a management on nutrition data provided by the user. It can calculate statistics on a given 
period of time and on a specific sector. User can use these data to find the _mean_ value of his weight, the _variance_ of 
his lipid intake or the _percentage_ of proteins above macronutrients. This rest api works using Edamam's free database,
which provides nutritional values on a given food name, and stores in a model (description below).

In order to calculate useful statistics some good data are needed, so the client should register his consumptions.
This can be done by adding `Food` by its name or by its ean code to one of the following meals of each day:
- `Breakfast`
- `Lunch`
- `Snack`
- `Dinner`

The food names or the ean codes provide new instances of `Food` filled with the values of the following nutrition filtered in the response
of Edamam/Chomp api:
- `Carbohydrate`
- `Protein`
- `Lipid`
- `Vitamin A`
- `Vitamin C`
- `Sodium`
- `Calcium`
- `Potassium`
- `Iron`
- `Fiber`
- `Water`

The client can also add the quantity of water drunk daily or update the value of his weight using the dedicated endpoints.
In the end all the data can be filtered and used to calculate the following statistics:
- `Mean`
- `Variance`
- `Percentage`

Filter the data means to specify a range of days or a meal type on which calculate the statistics. In fact the client
can filter his data:
- `By date`
- `By meal type`
- `By food name`
- `By nutrient`

***

<a name="endpoints"></a>
## 🌎 Application endpoints 🌎:
Type | Route | Params | Body | Description
---- | ---- | ---- | ---- | ----  
🟡**POST** | `/signup` | `nickname`, `email`, `birth`, `height`, `weight`, `diet`, `gender` | -| Register new user.
🟡**POST** | `/add/food/by_name` | `token`, `day_id`, `meal_type`, `food_name`, `portion_weight`, `unit_of_measure` |- | Add consumed food to a specified meal by its name.
🟡**POST** | `/add/food/by_ean` | `token`, `day_id`, `meal_type`, `ean_code`, `portion_weight` |- | Add consumed food to a specified meal by its name.
🟡**POST** | `/add/water` | `token`, `day_id`, `portion_volume` |- | Add consumed water to a specified meal.
🟡**POST** | `/stats` | `token`, `type` | `start_date`, `end_date`, `meal_type`, `food_name`, `nutrient_name[]` | Get all the stats for the current user, you can filter the response by days range and by witch stats you need.
🟡**POST** | `/filters` | `token` | `start_date`, `end_date`, `meal_type`, `food_name`, `nutrient_name[]` | Filter metadata by what you need.
🔴**DELETE** | `/reset` | `token` |- | Deletes all data owned by the user.
🔵**PUT** | `/update_weight` | `token`, `weight` |- | Update the value of user's weight.
🟢**GET** | `/api/name/{food_name}` | `portion_weight`, `unit_of_measure` | -| Return information about food by its name.
🟢**GET** | `/api/ean/{ean_code}` | - |- | Return information about product by its ean code.
🟢**GET** | [`/login`](https://nutritionstatsoop.herokuapp.com/login?token=3959de8aeefabfa1385135fa8d03ee21) | `token`|-  | Login user, return user's info if in the database.
🟢**GET** | [`/diary`](https://nutritionstatsoop.herokuapp.com/diary?token=3959de8aeefabfa1385135fa8d03ee21) | `token` |- | Return all the data (`metadata`) owned by the user.
🟢**GET** | [`/diary/{day_id}`](https://nutritionstatsoop.herokuapp.com/diary/23-12-2021?token=3959de8aeefabfa1385135fa8d03ee21) | `token` | -| Return the data of the user in specified day.

***

<a name="uml"></a>                                                     
## ☀ Application UML ☀:
### •🔰 PACKAGES:
<img alt="PACKAGES" height="400" src="graphics/UML%20Packages.drawio.jpg"/>

### •🟪 MODEL:
<img alt="MODEL" height="280" src="graphics/model.jpg"/>

### •🟦 NUTRIENT:
<img alt="NUTRIENT" height="280" src="graphics/nutrient.jpg"/>

### •🟩 EXCEPTION:
<img alt="EXCEPTION" height="280" src="graphics/exception.jpg"/>

### •🟨 ENUM:
<img alt="ENUM" height="280" src="graphics/enum.jpg"/>

### •🟧 FILTER:
<img alt="FILTER" height="280" src="graphics/filter.jpg"/>

### •🟥 STATISTIC:
<img alt="STATISTIC" height="280" src="graphics/statistic.jpg"/>

***

<a name="tools"></a>
## 🛠️ Used tools 🛠️
- ###### Software:

  - [Eclipse](https://www.eclipse.org/) - java IDE

  - [InteelliJ IDEA](https://www.jetbrains.com/idea/) - java IDE

  - [SpringBoot](https://spring.io/projects/spring-boot) - open source Java-based framework used to create a micro Service.

  - [PostMan](https://www.postman.com) - API platform for building and using APIs.

  - [Maven](https://maven.apache.org/) - Software project management and comprehension tool based on the concept of project object model (POM).
  
  - [Draw.io](https://app.diagrams.net/) - Diagram software used for UML

- ###### External REST API:

  - [Edamam](https://developer.edamam.com/) - First food and nutrition database API.

  - [Chomp](https://chompthis.com/api/) - Second food and nutrition database API.
  
  - [Dropbox](https://www.dropbox.com/developers/documentation/http/documentation) - Storage cloud used to backup the serialized objects.

***

<a name="authors"></a>
## 👥 Authors 👥
Name | Email | Github profile |
--|--|--|
Morelli Valerio|valeriomorelli50@gmail.com|[GitHub](https://github.com/MrPio)
Mazzatenta Davide|mazzada2001@gmail.com|[GitHub](https://github.com/Paccise)
