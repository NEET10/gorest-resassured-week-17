package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static int userId;
    @Test
    public void test001() {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName("Unknown Patel");
        postsPojo.setEmail("patel" + TestUtils.getRandomValue() + "@gmail.com");
        postsPojo.setGender("Male");
        postsPojo.setStatus("Active");
        userId = given()
                .header("Authorization", "Bearer 7a16b401f37b656d8f83aa37969fb33ebc99d693df152b58659d4749f5720094")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(postsPojo)
                .post("/users")
                .then().statusCode(201).extract().path("id");
    }

    @Test
    public void test002() { // Test to perform Put request
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Unknown Patel" + TestUtils.getRandomValue());
        userPojo.setEmail("patel" + TestUtils.getRandomValue() + "@gmail.com");
        userPojo.setStatus("Inactive");
        Response response = given()
                .header("Authorization", "Bearer 7a16b401f37b656d8f83aa37969fb33ebc99d693df152b58659d4749f5720094")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .put("/users/" + userId);
        response.then().statusCode(200);
    }

    @Test
    public void test003() { // Test to perform Delete request
        given()
                .header("Authorization", "Bearer 7a16b401f37b656d8f83aa37969fb33ebc99d693df152b58659d4749f5720094")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("id", userId)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(204);

        given()
                .header("Authorization", "Bearer 7a16b401f37b656d8f83aa37969fb33ebc99d693df152b58659d4749f5720094")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("id", userId)
                .when()
                .get("/users/{id}")
                .then().statusCode(404);
    }
}
