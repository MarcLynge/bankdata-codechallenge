package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testgetAccounts() {
        given()
          .when().get("/api/getAccounts")
          .then()
             .statusCode(200);
    }

    // Endpoint - /api/createAccount
    @Test
    public void testNegativeBalanceWhenCreatingUser() {
        given()
        .body("{\"username\": \"Bruce\", \"name\" : \"Bruce\", \"balance\": 1000, \"password\" : \"123\"}")
        .header("Content-Type", "application/json")
        .when()
        .post("/api/createAccount")
        .then()
        .statusCode(200);

        given()
        .body("{\"username\": \"Bruce\", \"name\" : \"Bruce\", \"balance\": -1000, \"password\" : \"123\"}")
        .header("Content-Type", "application/json")
        .when()
        .post("/api/createAccount")
        .then()
        .statusCode(400);
    }

    @Test
    public void testMissingUsernameWhenCreatingUser() {
        given()
        .body("{\"username\": \"Bruce\", \"name\" : \"Bruce\", \"balance\": 1000, \"password\" : \"123\"}")
        .header("Content-Type", "application/json")
        .when()
        .post("/api/createAccount")
        .then()
        .statusCode(200);

        given()
        .body("{\"username\": \"\", \"name\" : \"Bruce\", \"balance\": 1000, \"password\" : \"123\"}")
        .header("Content-Type", "application/json")
        .when()
        .post("/api/createAccount")
        .then()
        .statusCode(400);
    }

    @Test
    public void testMissingPasswordWhenCreatingUser() {
        given()
        .body("{\"username\": \"Bruce\", \"name\" : \"Bruce\", \"balance\": 1000, \"password\" : \"123\"}")
        .header("Content-Type", "application/json")
        .when()
        .post("/api/createAccount")
        .then()
        .statusCode(200);

        given()
        .body("{\"username\": \"\", \"name\" : \"Bruce\", \"balance\": 1000, \"password\" : \"\"}")
        .header("Content-Type", "application/json")
        .when()
        .post("/api/createAccount")
        .then()
        .statusCode(400);
    }
}