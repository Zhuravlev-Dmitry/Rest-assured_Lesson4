package org.example;

import io.restassured.RestAssured;
        import io.restassured.builder.RequestSpecBuilder;
        import io.restassured.builder.ResponseSpecBuilder;
        import io.restassured.filter.log.LogDetail;
        import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
        import io.restassured.specification.ResponseSpecification;
        import org.hamcrest.Matchers;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;
        import static org.hamcrest.CoreMatchers.*;
        import static org.hamcrest.MatcherAssert.assertThat;

public class PostCuisineTest  {

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
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

    }
    @Test
    void postExampleTest() {
        PostCuisineResponseClass response = given().spec(requestSpecification)
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(PostCuisineResponseClass.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
        assertThat(response.getCuisines().get(1), containsString("European"));
        assertThat(response.getConfidence(), equalTo(0.0));
    }

    @Test
    void postWithTitleTest() {
        PostCuisineResponseClass response = given().spec(requestSpecification)
                .when()
                .formParam("title","Corn avocado salsa")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(PostCuisineResponseClass.class);
        assertThat(response.getCuisine(), containsString("Mexican"));
        assertThat(response.getCuisines().get(0), containsString("Mexican"));
        assertThat(response.getConfidence(), equalTo(0.95));
    }
    @Test
    void postWithIngredientListTest() {
        PostCuisineResponseClass response = given().spec(requestSpecification)
                .when()
                .formParam("ingredientList","3 eggs")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(PostCuisineResponseClass.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
        assertThat(response.getCuisines().get(1), containsString("European"));
        assertThat(response.getConfidence(), equalTo(0.0));
    }
    @Test
    void postWithDeutschTitleTest() {
        PostCuisineResponseClass response = given().spec(requestSpecification)
                .when()
                .formParam("title","Dukguk (Korean Rice Cake Soup)")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(PostCuisineResponseClass.class);
        assertThat(response.getCuisine(), containsString("Korean"));
        assertThat(response.getCuisines().get(1), containsString("Asian"));
        assertThat(response.getConfidence(), equalTo(0.85));
    }

    @Test
    void postWithRussianTitleTest() {
        PostCuisineResponseClass response = given().spec(requestSpecification)
                .when()
                .formParam("title","Шашлыки!!!")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(PostCuisineResponseClass.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
        assertThat(response.getCuisines().get(2), containsString("Italian"));
        assertThat(response.getConfidence(), equalTo(0.0));
    }

}
