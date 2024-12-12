package org.example.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests extends BaseTest {

    @Test
    public void testGetEndpoint() {
        given()
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().all()// Loga a resposta
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    public void testPostEndpoint() {
        String requestBody = """
            {
                "title": "foo",
                "body": "bar",
                "userId": 1
            }
            """;

        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .log().all()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }
}
