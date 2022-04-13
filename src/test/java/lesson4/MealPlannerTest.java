package lesson4;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MealPlannerTest extends BaseTest {
    String templateName = "My new meal plan template";

    @Test
    @Order(1)
    void addMealPlanBreakfastTemplateTest() {
        JsonPath response = given()
                .body("{\n" +
                        "    \"name\": \"" + templateName + "\",\n" +
                        "    \"items\": [\n" +
                        "        {\n" +
                        "            \"day\": 1,\n" +
                        "            \"slot\": 1,\n" +
                        "            \"position\": 0,\n" +
                        "            \"type\": \"PRODUCT\",\n" +
                        "            \"value\": {\n" +
                        "            \"id\": 10286,\n" +
                        "            \"title\": \"Live Love Snack Potato Chips\"\n" +
                        "            }\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"day\": 1,\n" +
                        "            \"slot\": 2,\n" +
                        "            \"position\": 0,\n" +
                        "            \"type\": \"PRODUCT\",\n" +
                        "            \"value\":  {\n" +
                        "            \"id\": 30605,\n" +
                        "            \"title\": \"Campbell's Soup Chicken Noodle\"\n" +
                        "            }\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"day\": 1,\n" +
                        "            \"slot\": 3,\n" +
                        "            \"position\": 0,\n" +
                        "            \"type\": \"PRODUCT\",\n" +
                        "            \"value\": {\n" +
                        "            \"id\": 649443,\n" +
                        "            \"title\": \"Marie Morin Mango Cheesecake\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"publishAsPublic\": false\n" +
                        "}")
                .when()
                .post(baseUrl + mealPlannerUrl + templatesUrl + "?hash=" + hash)
                .prettyPeek()
                .then()
                .spec(responseSpecificationPositive)
                .extract()
                .jsonPath();

/*        String strResponse="{\"status\":\"success\",\"mealPlan\":{\"id\":4697,\"name\":\"My new meal plan template\",\"days\":[{\"nutritionSummary\":{\"nutrients\":[{\"name\":\"Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Caffeine\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Lycopene\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Copper\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Calories\",\"amount\":400.0,\"unit\":\"kcal\",\"percentOfDailyNeeds\":20},{\"name\":\"Calcium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Carbohydrates\",\"amount\":56.0,\"unit\":\"g\",\"percentOfDailyNeeds\":19},{\"name\":\"Net Carbohydrates\",\"amount\":53.0,\"unit\":\"g\",\"percentOfDailyNeeds\":19},{\"name\":\"Choline\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Cholesterol\",\"amount\":15.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":5},{\"name\":\"Fat\",\"amount\":16.5,\"unit\":\"g\",\"percentOfDailyNeeds\":25},{\"name\":\"Fluoride\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Trans Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Saturated Fat\",\"amount\":0.5,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Mono Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Poly Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Fiber\",\"amount\":3.0,\"unit\":\"g\",\"percentOfDailyNeeds\":12},{\"name\":\"Folate\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Folic Acid\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iodine\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iron\",\"amount\":0.36,\"unit\":\"mg\",\"percentOfDailyNeeds\":2},{\"name\":\"Magnesium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Manganese\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B3\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B5\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Phosphorus\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Potassium\",\"amount\":50.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":1},{\"name\":\"Protein\",\"amount\":9.0,\"unit\":\"g\",\"percentOfDailyNeeds\":18},{\"name\":\"Vitamin B2\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Selenium\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sodium\",\"amount\":1070.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":47},{\"name\":\"Vitamin B1\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin A\",\"amount\":200.0,\"unit\":\"IU\",\"percentOfDailyNeeds\":4},{\"name\":\"Vitamin B6\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B12\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin C\",\"amount\":1.65,\"unit\":\"mg\",\"percentOfDailyNeeds\":2},{\"name\":\"Vitamin D\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin E\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin K\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar\",\"amount\":1.0,\"unit\":\"g\",\"percentOfDailyNeeds\":1},{\"name\":\"Zinc\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0}]},\"nutritionSummaryBreakfast\":{\"nutrients\":[{\"name\":\"Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Caffeine\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Lycopene\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Copper\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Calories\",\"amount\":120.0,\"unit\":\"kcal\",\"percentOfDailyNeeds\":6},{\"name\":\"Calcium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Carbohydrates\",\"amount\":20.0,\"unit\":\"g\",\"percentOfDailyNeeds\":7},{\"name\":\"Net Carbohydrates\",\"amount\":18.0,\"unit\":\"g\",\"percentOfDailyNeeds\":7},{\"name\":\"Choline\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Cholesterol\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Fat\",\"amount\":4.5,\"unit\":\"g\",\"percentOfDailyNeeds\":7},{\"name\":\"Fluoride\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Trans Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Saturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Mono Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Poly Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Fiber\",\"amount\":2.0,\"unit\":\"g\",\"percentOfDailyNeeds\":8},{\"name\":\"Folate\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Folic Acid\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iodine\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iron\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Magnesium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Manganese\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B3\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B5\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Phosphorus\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Potassium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Protein\",\"amount\":2.0,\"unit\":\"g\",\"percentOfDailyNeeds\":4},{\"name\":\"Vitamin B2\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Selenium\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sodium\",\"amount\":180.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":8},{\"name\":\"Vitamin B1\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin A\",\"amount\":0.0,\"unit\":\"IU\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B6\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B12\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin C\",\"amount\":1.65,\"unit\":\"mg\",\"percentOfDailyNeeds\":2},{\"name\":\"Vitamin D\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin E\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin K\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Zinc\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0}]},\"nutritionSummaryLunch\":{\"nutrients\":[{\"name\":\"Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Caffeine\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Lycopene\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Copper\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Calories\",\"amount\":60.0,\"unit\":\"kcal\",\"percentOfDailyNeeds\":3},{\"name\":\"Calcium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Carbohydrates\",\"amount\":8.0,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Net Carbohydrates\",\"amount\":7.0,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Choline\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Cholesterol\",\"amount\":15.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":5},{\"name\":\"Fat\",\"amount\":2.0,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Fluoride\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Trans Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Saturated Fat\",\"amount\":0.5,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Mono Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Poly Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Fiber\",\"amount\":1.0,\"unit\":\"g\",\"percentOfDailyNeeds\":4},{\"name\":\"Folate\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Folic Acid\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iodine\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iron\",\"amount\":0.36,\"unit\":\"mg\",\"percentOfDailyNeeds\":2},{\"name\":\"Magnesium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Manganese\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B3\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B5\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Phosphorus\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Potassium\",\"amount\":50.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":1},{\"name\":\"Protein\",\"amount\":3.0,\"unit\":\"g\",\"percentOfDailyNeeds\":6},{\"name\":\"Vitamin B2\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Selenium\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sodium\",\"amount\":890.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":39},{\"name\":\"Vitamin B1\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin A\",\"amount\":200.0,\"unit\":\"IU\",\"percentOfDailyNeeds\":4},{\"name\":\"Vitamin B6\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B12\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin C\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin D\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin E\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin K\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar\",\"amount\":1.0,\"unit\":\"g\",\"percentOfDailyNeeds\":1},{\"name\":\"Zinc\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0}]},\"nutritionSummaryDinner\":{\"nutrients\":[{\"name\":\"Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Caffeine\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Lycopene\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Copper\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Calories\",\"amount\":220.0,\"unit\":\"kcal\",\"percentOfDailyNeeds\":11},{\"name\":\"Calcium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Carbohydrates\",\"amount\":28.0,\"unit\":\"g\",\"percentOfDailyNeeds\":9},{\"name\":\"Net Carbohydrates\",\"amount\":28.0,\"unit\":\"g\",\"percentOfDailyNeeds\":10},{\"name\":\"Choline\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Cholesterol\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Fat\",\"amount\":10.0,\"unit\":\"g\",\"percentOfDailyNeeds\":15},{\"name\":\"Fluoride\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Trans Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Saturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Mono Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Poly Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Fiber\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Folate\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Folic Acid\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iodine\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iron\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Magnesium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Manganese\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B3\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B5\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Phosphorus\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Potassium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Protein\",\"amount\":4.0,\"unit\":\"g\",\"percentOfDailyNeeds\":8},{\"name\":\"Vitamin B2\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Selenium\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sodium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B1\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin A\",\"amount\":0.0,\"unit\":\"IU\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B6\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B12\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin C\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin D\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin E\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin K\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Zinc\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0}]},\"day\":\"1\",\"items\":[{\"id\":107573,\"slot\":1,\"position\":0,\"type\":\"PRODUCT\",\"value\":{\"id\":10286,\"title\":\"Live Love Snack Potato Chips\"}},{\"id\":107575,\"slot\":2,\"position\":0,\"type\":\"PRODUCT\",\"value\":{\"id\":30605,\"title\":\"Campbell's Soup Chicken Noodle\"}},{\"id\":107577,\"slot\":3,\"position\":0,\"type\":\"PRODUCT\",\"value\":{\"id\":649443,\"title\":\"Marie Morin Mango Cheesecake\"}}]}]}}";
        JsonPath response = JsonPath.given(strResponse);
*/
        idMeal = response.get("mealPlan.id");
        System.out.println("\naddMealPlanBreakfastTemplateTest id: " + idMeal);

        assertThat(response.get("mealPlan.name"), equalTo(templateName));
        assertThat(response.get("mealPlan.days[0]"), hasKey("nutritionSummaryBreakfast"));
        assertThat(response.get("mealPlan.days[0]"), hasKey("nutritionSummaryLunch"));
        assertThat(response.get("mealPlan.days[0]"), hasKey("nutritionSummaryDinner"));
        assertThat(response.get("mealPlan.days[0]"), hasKey("nutritionSummaryDinner"));
        assertThat(response.get("mealPlan.days[0].day"), equalTo("1"));
        assertThat(response.get("mealPlan.days[0].items[2].slot"), equalTo(3));
        assertThat(response.get("mealPlan.days[0].items[2].value.title"), equalTo("Marie Morin Mango Cheesecake"));
    }

    @Test
    void getMealPlanTemplatesTest() {
        JsonPath response = given()
                .when()
                .get(baseUrl + mealPlannerUrl + templatesUrl + "?hash=" + hash)
                .then()
                .spec(responseSpecificationPositive)
                .extract()
                .jsonPath();

/*        String strResponse="{\"templates\":[{\"id\":4715,\"name\":\"My new meal plan template\"}]}";
        JsonPath response = JsonPath.given(strResponse);
*/
        int responseLength = ((ArrayList) response.get("templates")).size();
        if (responseLength > 0) {
            assertThat(response.get("templates[0]"), hasKey("id"));
            assertThat(response.get("templates[0]"), hasKey("name"));
            idMeal = response.get("templates[0].id");
            System.out.println("\ngetMealPlanTemplatesTest id: " + response.get("templates[0].id"));
        }
    }

    @Test
    void getMealPlanTemplateByIdTest() {
        /* ID-checking because there is a bug that an NullPointException is thrown if no entry is found for the id */
        System.out.println("\ngetMealPlanTemplateByIdTest id: " + idMeal);
        if (idMeal > 0) {
            JsonPath response = given()
                    .when()
                    .get(baseUrl + mealPlannerUrl + templatesUrl + "/" + idMeal + "?hash=" + hash)
                    .then()
                    .spec(responseSpecificationPositive)
                    .extract()
                    .jsonPath();

/*            String strResponse = "{\"id\":4723,\"name\":\"My new meal plan template\",\"days\":[{\"nutritionSummary\":{\"nutrients\":[{\"name\":\"Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Caffeine\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Lycopene\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Copper\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Calories\",\"amount\":400.0,\"unit\":\"kcal\",\"percentOfDailyNeeds\":20},{\"name\":\"Calcium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Carbohydrates\",\"amount\":56.0,\"unit\":\"g\",\"percentOfDailyNeeds\":19},{\"name\":\"Net Carbohydrates\",\"amount\":53.0,\"unit\":\"g\",\"percentOfDailyNeeds\":19},{\"name\":\"Choline\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Cholesterol\",\"amount\":15.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":5},{\"name\":\"Fat\",\"amount\":16.5,\"unit\":\"g\",\"percentOfDailyNeeds\":25},{\"name\":\"Fluoride\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Trans Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Saturated Fat\",\"amount\":0.5,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Mono Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Poly Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Fiber\",\"amount\":3.0,\"unit\":\"g\",\"percentOfDailyNeeds\":12},{\"name\":\"Folate\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Folic Acid\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iodine\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iron\",\"amount\":0.36,\"unit\":\"mg\",\"percentOfDailyNeeds\":2},{\"name\":\"Magnesium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Manganese\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B3\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B5\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Phosphorus\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Potassium\",\"amount\":50.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":1},{\"name\":\"Protein\",\"amount\":9.0,\"unit\":\"g\",\"percentOfDailyNeeds\":18},{\"name\":\"Vitamin B2\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Selenium\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sodium\",\"amount\":1070.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":47},{\"name\":\"Vitamin B1\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin A\",\"amount\":200.0,\"unit\":\"IU\",\"percentOfDailyNeeds\":4},{\"name\":\"Vitamin B6\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B12\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin C\",\"amount\":1.65,\"unit\":\"mg\",\"percentOfDailyNeeds\":2},{\"name\":\"Vitamin D\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin E\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin K\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar\",\"amount\":1.0,\"unit\":\"g\",\"percentOfDailyNeeds\":1},{\"name\":\"Zinc\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0}]},\"nutritionSummaryBreakfast\":{\"nutrients\":[{\"name\":\"Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Caffeine\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Lycopene\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Copper\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Calories\",\"amount\":120.0,\"unit\":\"kcal\",\"percentOfDailyNeeds\":6},{\"name\":\"Calcium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Carbohydrates\",\"amount\":20.0,\"unit\":\"g\",\"percentOfDailyNeeds\":7},{\"name\":\"Net Carbohydrates\",\"amount\":18.0,\"unit\":\"g\",\"percentOfDailyNeeds\":7},{\"name\":\"Choline\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Cholesterol\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Fat\",\"amount\":4.5,\"unit\":\"g\",\"percentOfDailyNeeds\":7},{\"name\":\"Fluoride\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Trans Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Saturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Mono Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Poly Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Fiber\",\"amount\":2.0,\"unit\":\"g\",\"percentOfDailyNeeds\":8},{\"name\":\"Folate\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Folic Acid\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iodine\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iron\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Magnesium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Manganese\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B3\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B5\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Phosphorus\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Potassium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Protein\",\"amount\":2.0,\"unit\":\"g\",\"percentOfDailyNeeds\":4},{\"name\":\"Vitamin B2\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Selenium\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sodium\",\"amount\":180.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":8},{\"name\":\"Vitamin B1\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin A\",\"amount\":0.0,\"unit\":\"IU\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B6\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B12\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin C\",\"amount\":1.65,\"unit\":\"mg\",\"percentOfDailyNeeds\":2},{\"name\":\"Vitamin D\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin E\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin K\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Zinc\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0}]},\"nutritionSummaryLunch\":{\"nutrients\":[{\"name\":\"Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Caffeine\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Lycopene\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Copper\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Calories\",\"amount\":60.0,\"unit\":\"kcal\",\"percentOfDailyNeeds\":3},{\"name\":\"Calcium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Carbohydrates\",\"amount\":8.0,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Net Carbohydrates\",\"amount\":7.0,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Choline\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Cholesterol\",\"amount\":15.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":5},{\"name\":\"Fat\",\"amount\":2.0,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Fluoride\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Trans Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Saturated Fat\",\"amount\":0.5,\"unit\":\"g\",\"percentOfDailyNeeds\":3},{\"name\":\"Mono Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Poly Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Fiber\",\"amount\":1.0,\"unit\":\"g\",\"percentOfDailyNeeds\":4},{\"name\":\"Folate\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Folic Acid\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iodine\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iron\",\"amount\":0.36,\"unit\":\"mg\",\"percentOfDailyNeeds\":2},{\"name\":\"Magnesium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Manganese\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B3\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B5\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Phosphorus\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Potassium\",\"amount\":50.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":1},{\"name\":\"Protein\",\"amount\":3.0,\"unit\":\"g\",\"percentOfDailyNeeds\":6},{\"name\":\"Vitamin B2\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Selenium\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sodium\",\"amount\":890.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":39},{\"name\":\"Vitamin B1\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin A\",\"amount\":200.0,\"unit\":\"IU\",\"percentOfDailyNeeds\":4},{\"name\":\"Vitamin B6\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B12\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin C\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin D\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin E\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin K\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar\",\"amount\":1.0,\"unit\":\"g\",\"percentOfDailyNeeds\":1},{\"name\":\"Zinc\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0}]},\"nutritionSummaryDinner\":{\"nutrients\":[{\"name\":\"Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar Alcohol\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Caffeine\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Lycopene\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Copper\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Calories\",\"amount\":220.0,\"unit\":\"kcal\",\"percentOfDailyNeeds\":11},{\"name\":\"Calcium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Carbohydrates\",\"amount\":28.0,\"unit\":\"g\",\"percentOfDailyNeeds\":9},{\"name\":\"Net Carbohydrates\",\"amount\":28.0,\"unit\":\"g\",\"percentOfDailyNeeds\":10},{\"name\":\"Choline\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Cholesterol\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Fat\",\"amount\":10.0,\"unit\":\"g\",\"percentOfDailyNeeds\":15},{\"name\":\"Fluoride\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Trans Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Saturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Mono Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Poly Unsaturated Fat\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Fiber\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Folate\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Folic Acid\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iodine\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Iron\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Magnesium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Manganese\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B3\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B5\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Phosphorus\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Potassium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Protein\",\"amount\":4.0,\"unit\":\"g\",\"percentOfDailyNeeds\":8},{\"name\":\"Vitamin B2\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Selenium\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sodium\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B1\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin A\",\"amount\":0.0,\"unit\":\"IU\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B6\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin B12\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin C\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin D\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin E\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0},{\"name\":\"Vitamin K\",\"amount\":0.0,\"unit\":\"µg\",\"percentOfDailyNeeds\":0},{\"name\":\"Sugar\",\"amount\":0.0,\"unit\":\"g\",\"percentOfDailyNeeds\":0},{\"name\":\"Zinc\",\"amount\":0.0,\"unit\":\"mg\",\"percentOfDailyNeeds\":0}]},\"day\":\"1\",\"items\":[{\"id\":107651,\"slot\":1,\"position\":0,\"type\":\"PRODUCT\",\"value\":{\"id\":10286,\"title\":\"Live Love Snack Potato Chips\"}},{\"id\":107653,\"slot\":2,\"position\":0,\"type\":\"PRODUCT\",\"value\":{\"id\":30605,\"title\":\"Campbell's Soup Chicken Noodle\"}},{\"id\":107655,\"slot\":3,\"position\":0,\"type\":\"PRODUCT\",\"value\":{\"id\":649443,\"title\":\"Marie Morin Mango Cheesecake\"}}]}]}";
            JsonPath response = JsonPath.given(strResponse);
*/
            assertThat(response.get("id"), equalTo(idMeal));
            assertThat(response.get("name"), equalTo(templateName));
            assertThat(response.get("$"), hasKey("days"));
            assertThat(response.get("days[0]"), hasKey("nutritionSummaryBreakfast"));

        }
    }
}
