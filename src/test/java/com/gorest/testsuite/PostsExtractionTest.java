package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Test
    public void test001() { // Test to extract the title of a particular record
        System.out.println("The Title Of ID 40499 : " + response.extract().path("findAll{it.id == 40499 }.title"));
    }

    @Test
    public void test002() { // Test to extract total number of records
        System.out.println("The Total Number Of Records : " + response.extract().path("size()"));
    }

    @Test
    public void test003() { // Test to extract the response body of a particular record
        System.out.println("The Response Body Of Record Number 6 : " + response.extract().path("findAll{it.id}.body[5]"));
    }

    @Test
    public void test004() { // Test to extract all user_id
        System.out.println("The User IDs Of All Records : " + response.extract().path("findAll{it.id}.user_id"));
    }

    @Test
    public void test005() { // Test to extract all titles from the response body
        System.out.println("All Titles From The Response Body : " + response.extract().path("findAll{it.id}.title"));
    }

    @Test // Test to extract the response body of by id
    public void test006() {
        System.out.println("The Response Body Of Record Having ID As 40484 : " + response.extract().path("findAll{it.id == 40484}.body"));
    }
}
