package lesson4;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import lesson4.pojo.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShoppingListTest extends BaseTest {
    String pathResources = "src/test/resources/";
    List<String> unitsList = Arrays.asList("kg", "lb", "pkg", "package");

    @Test
    @Order(1)
    void addItemToShoppingListTest() throws IOException {
        /* Set fields with setter */
//        InputAddListItem inputAddListItem = new InputAddListItem();
//        inputAddListItem.setItem("1 kg hazelnut flour");
//        inputAddListItem.setAisle("Baking");
//        inputAddListItem.setParse(true);
//        /* Set fields with ObjectMapper and String as Json  */
//        String strJson = "{\"item\":\"1 kg hazelnut flour\",\"aisle\": \"Baking\",\"parse\": true}";

        String strJson = Files.readString(Path.of(pathResources + "shoppingListItem.json"));
        InputAddListItem inputAddListItem = new ObjectMapper().readValue(strJson, InputAddListItem.class);

        ResponseAddedListItem responseAddedListItem = given()
                .contentType(ContentType.JSON)
                .body(inputAddListItem)
                .when()
                .post(baseUrl + mealPlannerUrl + shoppingListItemsUrl + "?hash=" + hash)
//                .prettyPeek()
                .then()
                .spec(responseSpecificationPositive)
                .extract()
                .body()
                .as(ResponseAddedListItem.class);

        idShoppingList = responseAddedListItem.getId();
        System.out.println("\naddItemToShoppingListTest id: " + idShoppingList);

        assertNotNull(responseAddedListItem.getId());
        assert (inputAddListItem.getItem().toLowerCase().contains(responseAddedListItem.getName().toLowerCase()));
        assert (responseAddedListItem.getAisle().equals(inputAddListItem.getAisle()));
        assert (unitsList.contains(responseAddedListItem.getMeasures().getOriginal().getUnit()));
        assert (unitsList.contains(responseAddedListItem.getMeasures().getMetric().getUnit()));
        assert (unitsList.contains(responseAddedListItem.getMeasures().getUs().getUnit()));
        assertNotNull(responseAddedListItem.getMeasures().getOriginal().getAmount());
        assertNotNull(responseAddedListItem.getMeasures().getMetric().getAmount());
        assertNotNull(responseAddedListItem.getMeasures().getUs().getAmount());
    }

    @Test
    @Order(3)
    void deleteShoppingListItemTest() {
        if (idShoppingList > 0) {
            JsonPath response = given()
                    .when()
                    .delete(baseUrl + mealPlannerUrl + shoppingListItemsUrl + "/" + idShoppingList + "?hash=" + hash)
//                    .prettyPeek()
                    .then()
                    .spec(responseSpecificationPositive)
                    .extract()
                    .jsonPath();

            String res = response.get("status");
            System.out.println("\ndeleteShoppingListItemTest: " + res);
            assertThat(response.get("status"), equalTo("success"));
            idShoppingList = 0;
        }
    }

    @Test
    @Order(4)
    void deleteShoppingListItemNotExistsTest() {
        JsonPath response = given()
                .when()
                .delete(baseUrl + mealPlannerUrl + shoppingListItemsUrl + "/" + idShoppingList + "?hash=" + hash)
                //               .prettyPeek()
                .then()
                .spec(responseSpecificationNegativeNotFound)
                .extract()
                .jsonPath();

        String status = response.get("status");
        System.out.println("deleteShoppingListItemNotExistsTest: " + status);

        assertThat(response.get("status"), equalTo("failure"));
        assertThat(response.get("code"), equalTo(404));
        assertThat(response.get("message"), containsString("does not exist"));

    }

    @Test
    @Order(2)
    void getShoppingListTest() {
        ResponseGetListItems responseGetListItems = given()
                .when()
                .get(baseUrl + mealPlannerUrl + shoppingListUrl + "?hash=" + hash)
//               .prettyPeek()
                .then()
                .spec(responseSpecificationPositive)
                .extract()
                .body()
                .as(ResponseGetListItems.class);

        double totalCost = 0;
        for (Aisle aisle : responseGetListItems.getAisles()) {
            for (Item item : aisle.getItems()) {
                assertNotNull(item.getId());
                assert (unitsList.contains(item.getMeasures().getOriginal().getUnit()));
                assert (unitsList.contains(item.getMeasures().getMetric().getUnit()));
                assert (unitsList.contains(item.getMeasures().getUs().getUnit()));
                assertNotNull(item.getMeasures().getOriginal().getAmount());
                assertNotNull(item.getMeasures().getMetric().getAmount());
                assertNotNull(item.getMeasures().getUs().getAmount());
                totalCost += item.getCost();
            }
        }
        assert (responseGetListItems.getCost().equals(totalCost));
    }

}


