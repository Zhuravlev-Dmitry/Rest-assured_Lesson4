package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class ShoppingListTest {
    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;
    private final String apiKey = "62e8122bf46941569505d78f6d632a72";
    private final String hash="985916c236586c57ac863c7f0a0f5d2a6bcb979d";

    @BeforeEach
    void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .build();

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("hash", hash)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void addShoppingListTest() {
        String id = given().spec(requestSpecification)
                .when()
                .body("{\n" +
                "    \"item\": \"1 package baking powder\",\n" +
                "    \"aisle\": \"Baking\",\n" +
                "    \"parse\": true,\n" +
                "}")
                .post("https://api.spoonacular.com/mealplanner/dmitrygb2/shopping-list/items").prettyPeek()
                .then()
                 .extract()
                 .body()
                 .jsonPath()
                 .get("id")
                 .toString();

         given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/mealplanner/dmitrygb2/shopping-list").prettyPeek()
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecification)
                .delete("https://api.spoonacular.com/mealplanner/dmitrygb2/shopping-list/items/" + id)
                .then()
                .spec(responseSpecification);

    }

}

