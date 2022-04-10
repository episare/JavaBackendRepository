import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
    static String postUrl;
    static String getUrl;
    static String mealUrl;
    static int id;

    @BeforeAll
    static void BeforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        getProperties();
        apiKey = properties.getProperty("apiKey");
        baseUrl = properties.getProperty("baseUrl");
        getUrl = properties.getProperty("getUrl");
        postUrl = properties.getProperty("postUrl");
        password = properties.getProperty("spoonacularPassword");
        username = properties.getProperty("username");
        hash = properties.getProperty("hash");
        mealUrl = "/mealplanner/"+username+"/templates/";
        id=0;
    }

    private static void getProperties() {
        try {
            InputStream output = new FileInputStream("src/test/resources/my.properties");
            properties.load(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void AfterAll() {
        if (id > 0) {
            JsonPath response = given()
                    .queryParam("apiKey", apiKey)
                    .queryParam("hash", hash)
                    .when()
                    .delete(baseUrl + mealUrl + id + "?hash=" + hash)
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath();
            String res = response.get("status");
            System.out.println("@AfterAll - DeleteMealPlanTemplate: " + res);
        }
     }
}

