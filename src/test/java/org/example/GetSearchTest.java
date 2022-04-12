package org.example;

import io.restassured.RestAssured;
        import io.restassured.builder.RequestSpecBuilder;
        import io.restassured.builder.ResponseSpecBuilder;
        import io.restassured.filter.log.LogDetail;
        import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
        import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GetSearchTest {

    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;
    private final String apiKey = "f0bef4f38c6b400ba2ccf4012c1c5c61";

    @BeforeEach
    void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(4000L))
                .build();

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("addRecipeNutrition", "false")
                .addQueryParam("number", "1")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
    @Test
    void getPositiveTest() {
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getWithQueryPositiveTest() {
        GetResponseSearchClass response = given().spec(requestSpecification)
                .queryParam("query", "bread")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .extract()
                .response()
                .body()
                .as(GetResponseSearchClass.class);
           assertThat(response.getOffset(), equalTo(0));
           assertThat(response.getNumber(),equalTo(1));
           assertThat(response.getTotalResults(),equalTo(175));
    }


    @Test
    void getCheckSearchWithTypeTest() {
        GetResponseSearchClass response = given().spec(requestSpecification)
                .queryParam("type", "soup")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(GetResponseSearchClass.class);
        assertThat(response.getOffset(), equalTo(0));
        assertThat(response.getTotalResults(),equalTo(320));
        assertThat(response.getResults().toString(),containsString("Soup"));
    }
    @Test
    void getCheckMinSugarTest() {
         given().spec(requestSpecification)
                 .queryParam("query", "cake")
                 .queryParam("minSugar", 40)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch").prettyPeek()
                .then()
                .spec(responseSpecification);

    }
    @Test
    void getCheckRequestTest() {
        GetResponseSearchClass response = given().spec(requestSpecification)
                .queryParam("query", "cake")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(GetResponseSearchClass.class);
        assertThat(response.getResults().toString(),containsString("Cake"));
        assertThat(response.getResults().toString(),containsString("id=1161745"));
    }

}
