

import java.time.LocalDate;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.univpm.po.NutritionStats.service.filter.FilterByDate;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

class FilterTest {

	JSONObject j;
	
	@BeforeEach
    void setUp() {
        try {
			j = (JSONObject) new JSONParser().parse("{\"user\":{\"diary\":{\"user\":{\"nickname\":\"Valerio_Morelli\",\"email\":\"valeriomorelli50@gmail.com\",\"diet\":\"CLASSIC\",\"gender\":\"MALE\",\"height\":180,\"weight\":{\"2021-12-12\":77.0,\"2021-12-18\":77.0},\"dailyCaloricIntake\":0,\"yearOfBirth\":\"2001-06-07\"},\"dayList\":[{\"date\":\"2021-11-15\",\"mealList\":[{\"mealType\":\"LUNCH\",\"foodList\":[{\"name\":\"candy\",\"portionWeight\":200,\"measure\":\"GR\",\"diet\":\"VEGAN\",\"nutrientList\":[{\"name\":\"CARBOHYDRATE\",\"measure\":\"GR\",\"quantity\":127.8,\"quantityOnlySugar\":109.0},{\"name\":\"PROTEIN\",\"measure\":\"GR\",\"quantity\":8.4},{\"name\":\"LIPID\",\"measure\":\"GR\",\"quantity\":60.0,\"quantityOnlySatured\":35.5},{\"name\":\"VITAMIN_A\",\"measure\":\"GR\",\"quantity\":0.0},{\"name\":\"VITAMIN_C\",\"measure\":\"GR\",\"quantity\":0.0},{\"name\":\"CALCIUM\",\"measure\":\"GR\",\"quantity\":0.064},{\"name\":\"POTASSIUM\",\"measure\":\"GR\",\"quantity\":0.022000002},{\"name\":\"POTASSIUM\",\"measure\":\"GR\",\"quantity\":0.73},{\"name\":\"IRON\",\"measure\":\"GR\",\"quantity\":0.0062600006}],\"notNutrientList\":[{\"name\":\"WATER_FROM_FOOD\",\"quantity\":1.4},{\"name\":\"FIBER\",\"quantity\":11.8}],\"calories\":1084},{\"name\":\"candy\",\"portionWeight\":200,\"measure\":\"GR\",\"diet\":\"VEGAN\",\"nutrientList\":[{\"name\":\"CARBOHYDRATE\",\"measure\":\"GR\",\"quantity\":127.8,\"quantityOnlySugar\":109.0},{\"name\":\"PROTEIN\",\"measure\":\"GR\",\"quantity\":8.4},{\"name\":\"LIPID\",\"measure\":\"GR\",\"quantity\":60.0,\"quantityOnlySatured\":35.5},{\"name\":\"VITAMIN_A\",\"measure\":\"GR\",\"quantity\":0.0},{\"name\":\"VITAMIN_C\",\"measure\":\"GR\",\"quantity\":0.0},{\"name\":\"CALCIUM\",\"measure\":\"GR\",\"quantity\":0.064},{\"name\":\"POTASSIUM\",\"measure\":\"GR\",\"quantity\":0.022000002},{\"name\":\"POTASSIUM\",\"measure\":\"GR\",\"quantity\":0.73},{\"name\":\"IRON\",\"measure\":\"GR\",\"quantity\":0.0062600006}],\"notNutrientList\":[{\"name\":\"WATER_FROM_FOOD\",\"quantity\":1.4},{\"name\":\"FIBER\",\"quantity\":11.8}],\"calories\":1084},{\"name\":\"pizza\",\"portionWeight\":200,\"measure\":\"GR\",\"diet\":\"VEGETARIAN\",\"nutrientList\":[{\"name\":\"CARBOHYDRATE\",\"measure\":\"GR\",\"quantity\":58.04,\"quantityOnlySugar\":7.14},{\"name\":\"PROTEIN\",\"measure\":\"GR\",\"quantity\":20.72},{\"name\":\"LIPID\",\"measure\":\"GR\",\"quantity\":24.56,\"quantityOnlySatured\":8.544},{\"name\":\"VITAMIN_A\",\"measure\":\"GR\",\"quantity\":1.32E-4},{\"name\":\"VITAMIN_C\",\"measure\":\"GR\",\"quantity\":0.0024},{\"name\":\"CALCIUM\",\"measure\":\"GR\",\"quantity\":0.358},{\"name\":\"POTASSIUM\",\"measure\":\"GR\",\"quantity\":0.89400005},{\"name\":\"POTASSIUM\",\"measure\":\"GR\",\"quantity\":0.30400002},{\"name\":\"IRON\",\"measure\":\"GR\",\"quantity\":0.00454}],\"notNutrientList\":[{\"name\":\"WATER_FROM_FOOD\",\"quantity\":92.56},{\"name\":\"FIBER\",\"quantity\":4.4}],\"calories\":536},{\"name\":\"pizza\",\"portionWeight\":200,\"measure\":\"GR\",\"diet\":\"VEGETARIAN\",\"nutrientList\":[{\"name\":\"CARBOHYDRATE\",\"measure\":\"GR\",\"quantity\":58.04,\"quantityOnlySugar\":7.14},{\"name\":\"PROTEIN\",\"measure\":\"GR\",\"quantity\":20.72},{\"name\":\"LIPID\",\"measure\":\"GR\",\"quantity\":24.56,\"quantityOnlySatured\":8.544},{\"name\":\"VITAMIN_A\",\"measure\":\"GR\",\"quantity\":1.32E-4},{\"name\":\"VITAMIN_C\",\"measure\":\"GR\",\"quantity\":0.0024},{\"name\":\"CALCIUM\",\"measure\":\"GR\",\"quantity\":0.358},{\"name\":\"POTASSIUM\",\"measure\":\"GR\",\"quantity\":0.89400005},{\"name\":\"POTASSIUM\",\"measure\":\"GR\",\"quantity\":0.30400002},{\"name\":\"IRON\",\"measure\":\"GR\",\"quantity\":0.00454}],\"notNutrientList\":[{\"name\":\"WATER_FROM_FOOD\",\"quantity\":92.56},{\"name\":\"FIBER\",\"quantity\":4.4}],\"calories\":536}]}],\"waterList\":[]},{\"date\":\"2021-11-11\",\"mealList\":[],\"waterList\":[{\"volume\":350,\"sodium\":0.014000001,\"calcium\":0.0105},{\"volume\":350,\"sodium\":0.014000001,\"calcium\":0.0105}]}]}}}");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	void test() {
		FilterByDate f = new FilterByDate(j);
		System.out.print(f.filter(LocalDate.parse("2021/11/11"),LocalDate.parse( "2021/11/15")));
		
	}

}
