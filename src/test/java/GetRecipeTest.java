import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetRecipeTest extends BaseTest {
    static int totalNumberOfRecipes;
    static int resultsByType;

    String existingCuisine = "Italian";
    String notExistingCuisine = "Russian";
    String existingType = "soup";
    String notExistingType = "floppy";
    int defaultNumber = 10;
    String titleMatch = "Snack";
    float minFat = 4;
    float maxFat = 10;

    @Test
    @Order(1)
    void getComplexSearchByNumber0Test() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("number", "0")
                .when()
                .get(baseUrl + getUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("number"), equalTo(1));
        totalNumberOfRecipes = response.get("totalResults");
    }

    @Test
    @Order(2)
    void getComplexSearchByExistingTypeTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("type", existingType)
                .when()
                .get(baseUrl + getUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("totalResults"), greaterThan(0));
        assertThat(response.get("number"), equalTo(defaultNumber));
        resultsByType = response.get("totalResults");
    }

    @Test
    void getComplexSearchByNumber101Test() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("number", "101")
                .when()
                .get(baseUrl + getUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("number"), equalTo(100));
    }

    @Test
    void getComplexSearchByNotExistingCuisineTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine", notExistingCuisine)
                .when()
                .get(baseUrl + getUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("totalResults"), equalTo(0));
        assertThat(response.get("number"), equalTo(defaultNumber));
    }

    @Test
    void getComplexSearchByNotExistingTypeTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("type", notExistingType)
                .when()
                .get(baseUrl + getUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("totalResults"), equalTo(totalNumberOfRecipes));
        assertThat(response.get("number"), equalTo(defaultNumber));
    }

    @Test
    void getComplexSearchByNotExistingCuisineWithExistingTypeTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine", notExistingCuisine)
                .queryParam("type", existingType)
                .when()
                .get(baseUrl + getUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        assertThat(response.get("totalResults"), equalTo(resultsByType));
        assertThat(response.get("number"), equalTo(defaultNumber));
    }

    @Test
    void getComplexSearchByTitleMatchTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("titleMatch", titleMatch)
                .when()
                .get(baseUrl + getUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

        assertThat(response.get("results.title"), everyItem(containsString(titleMatch)));
        assertThat(response.get("number"), equalTo(defaultNumber));
    }

    @Test
    void getComplexSearchWithIntervalNutritionFatTest() {
        List<ArrayList> response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine", existingCuisine)
                .queryParam("minFat", minFat)
                .queryParam("maxFat", maxFat)
                .when()
                .get(baseUrl + getUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results.nutrition.nutrients");

        for (int i = 0; i < response.size(); i++) {
            float amount = (Float) ((HashMap) response.get(i).get(0)).get("amount");
            // System.out.println("getComplexSearchWithIntervalNutritionFatTest amount:" + amount);
            assertThat(amount, both(greaterThanOrEqualTo(minFat)).and(lessThanOrEqualTo(maxFat)));
        }

    }
}

