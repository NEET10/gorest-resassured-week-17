package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = ;
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20'")
                .then().statusCode(200);
    }
    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size", equalTo(20));
    }
    @Test // Test to verify that a particular id has the required name
    public void test002() {
        response.body("findAll{it.id == 2368371}.name", hasItem("Mr. Bishnu Guneta"));
    }
    //2. Verify the if the name of id = 2368369 is equal to ”Kamla Gupta”
   @Test
    public void test003() {
        response.body("[2].name", equalTo("Kamla Gupta"));
    }
    //3. Check the single ‘Name’ in the Array list (Avadhesh Singh)
    @Test
    public void test004() {
        response.body("[3].name", equalTo("Avadhesh Singh"));
    }
    
    //4. Check the multiple ‘Names’ in the ArrayList (Veda Kaur, Minakshi Arora,
    //Menaka Desai)
    @Test
    public void test005() {
        response.body("findAll{it.id}.name", hasItems("Veda Kaur", "Minakshi Arora", "Menaka Desai"));
    }

        //5. Verify the emai of userid = 2368356 is equal “arora_pranay@marks-emard.example”
        @Test
        public void test006() {
            response.body("findAll{it.id == 2368356}.email", hasItem("arora_pranay@marks-emard.example"));
        }


        @Test
    public void test007() { // Test to verify that the name Anand Swarup Chopra has status as active
        response.body("findAll{it.name == 'Anand Swarup Chopra'}.status", hasItem("active"));
    }
    //7. Verify the Gender = male of user name is “Hiranya Kocchar”
    @Test
    public void test008() {
        response.body("findAll{it.name == 'Hiranya Kocchar'}.gender", hasItem("male"));
    }

    }

