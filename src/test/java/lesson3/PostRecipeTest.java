package lesson3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostRecipeTest extends BaseTest {

    @Test
    void classifyCuisineForAfricanTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .queryParam("language", "en")
                .body("title=African Chicken Peanut Stew")
                .when()
                .post(baseUrl + postUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

        assertThat(response.get("cuisine"), containsString("African"));

    }

    @Test
    void classifyCuisineForItalianTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .queryParam("language", "en")
                .body("title=Turkey Tomato Cheese Pizza")
                .when()
                .post(baseUrl + postUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

/*        String strResponse="{\"cuisine\":\"Mediterranean\",\"cuisines\":[\"Mediterranean\",\"European\",\"Italian\"],\"confidence\":0.0}";
        JsonPath response = JsonPath.given(strResponse);
*/
        assertThat(response.get("cuisine"), containsString("Mediterranean"));
        assertThat(response.get("cuisines"), hasItem("Italian"));
    }

    @Test
    void classifyCuisineByMatchTitleTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .queryParam("language", "en")
                .body("title=Russian")
                .when()
                .post(baseUrl + postUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

        assertThat(response.get("cuisine"), containsString("Eastern European"));
        assertThat(response.get("cuisines"), hasItem("European"));
    }

    @Test
    void classifyCuisineWithoutBodyTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .queryParam("language", "en")
                .when()
                .post(baseUrl + postUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

        assertThat(response.get("cuisine"), containsString("Mediterranean"));

    }

    @Test
    void classifyCuisineByNotExistingTitleTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .queryParam("language", "en")
                .body("title=????????")
                .when()
                .post(baseUrl + postUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

        assertThat(response.get("cuisine"), containsString("Mediterranean"));

    }
}
