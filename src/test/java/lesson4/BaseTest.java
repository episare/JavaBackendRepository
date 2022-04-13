package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {
    static Properties properties = new Properties();
    static String password;
    static String hash;
    static String username;
    static String apiKey;
    static String baseUrl;
    static String pathProperties = "src/test/resources/my.properties";
    static int idMeal;
    static int idShoppingList;
    static String mealPlannerUrl;
    static String templatesUrl = "/templates";
    static String shoppingListUrl = "/shopping-list";
    static String shoppingListItemsUrl = "/shopping-list/items";
    static ResponseSpecification responseSpecificationPositive;
    static ResponseSpecification responseSpecificationNegativeNotFound;

    @BeforeAll
    static void BeforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        getProperties();
        password = properties.getProperty("spoonacularPassword");
        username = properties.getProperty("username");
        hash = properties.getProperty("hash");
        apiKey = properties.getProperty("apiKey");
        baseUrl = properties.getProperty("baseUrl");
        mealPlannerUrl = "/mealplanner/" + username;
        idMeal = 0;
        idShoppingList = 0;

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("hash", hash)
                //               .log(LogDetail.ALL)
                .build();

        responseSpecificationPositive = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        responseSpecificationNegativeNotFound = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectStatusLine("HTTP/1.1 404 Not Found")
                .build();

    }

    @AfterAll
    static void AfterAll() {
        if (idMeal > 0) {
            JsonPath response = given()
                    .when()
                    .delete(baseUrl + mealPlannerUrl + templatesUrl + "/" + idMeal + "?hash=" + hash)
                    .prettyPeek()
                    .then()
                    .spec(responseSpecificationPositive)
                    .extract()
                    .jsonPath();
            String res = response.get("status");
            System.out.println("@AfterAll - DeleteMealPlanTemplate: " + res);
        }
        if (idShoppingList > 0) {
            JsonPath response = given()
                    .when()
                    .delete(baseUrl + mealPlannerUrl + shoppingListItemsUrl + "/" + idShoppingList + "?hash=" + hash)
                    .prettyPeek()
                    .then()
                    .spec(responseSpecificationPositive)
                    .extract()
                    .jsonPath();
            String res = response.get("status");
            System.out.println("@AfterAll - DeleteShoppingList: " + res);
        }
    }

    private static void getProperties() {
        try {
            InputStream output = new FileInputStream(pathProperties);
            properties.load(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
