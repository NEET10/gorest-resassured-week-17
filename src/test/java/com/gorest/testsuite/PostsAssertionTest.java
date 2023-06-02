package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
      //  RestAssured.port = ;
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
    }
    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(25));
    }
    @Test
    public void test002() { // Test to verify the title of from ID
        response.body("findAll{it.id == 40506}.title", hasItem("Tabula nisi cunabula earum est."));
    }

    @Test
    public void test003() { // Test to verify that a particular ID exists or not
        response.body("findAll{it}.id", hasItem(40493));
    }

    @Test
    public void test004() { // Test to verify That multiple IDs exist
        response.body("findAll{it}.id", hasItems(40483, 40495, 40507));
    }

    @Test
    public void test005() { // Test to verify the body of an ID
        response.body("findAll{it.id == 40500}.body", hasItem("Tempus vilis certo. Et acquiro amissio. Vero sperno et. Ater commemoro audacia. Veritas uter bibo. Apto officia attero. Peccatus aedificium quia. Aegrus argentum arbitro. Coniecto iure ut. Soluta capillus tamisium. Cupiditate animadverto thymbra. Possimus solvo pauci. Odio stipes quae. Sit tamquam vinculum. Calcar id cultura. Voluptatem arguo civis. Texo cupiditate temeritas. Somniculosus comitatus adfectus. Nobis copiose absorbeo."));
    }

}
