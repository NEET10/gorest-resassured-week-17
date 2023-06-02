package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("users")
                .then().statusCode(200);
    }

    @Test
    public void test001() { // Test to extract all IDs
        List<?> allIds = response.extract().path("id");
        System.out.println("The List Of All IDs : " + allIds);
    }

    @Test
    public void test002() { // Test to extract all names
        List<String> allNames = response.extract().path("name");
        System.out.println("The List Of All Names : " + allNames);
    }

    @Test
    public void test003() { // Test to extract the name of 5th object
        String name = response.extract().path("name[4]");
        System.out.println("The Name On The 5th Object : " + name);
    }

    @Test
    public void test004() { // Test to extract all names having inactive status
        List<String> allNamesWithStatusInactive = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("The List Of Names Having Inactive Status : " + allNamesWithStatusInactive);
    }

    @Test
    public void test005() { // Test to extract all genders having active status
        List<String> allGendersWithStatusActive = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("The List Of All Genders Having Active Status : " + allGendersWithStatusActive);
    }

    @Test
    public void test006() { // Test to extract all names having female gender
        List<String> allNamesWithGenderFemale = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("The List Of All Names Having Female Gender : " + allNamesWithGenderFemale);
    }

    @Test
    public void test007() { // Test to extract all emails having inactive status
        List<String> allEmailsWithStatusInactive = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("The List Of Emails having Inactive Status : " + allEmailsWithStatusInactive);
    }

    @Test
    public void test008() { // Test to extract all IDs having male gender
        List<?> allIdsWithMaleGender = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("The List Of All IDs Having Male Gender : " + allIdsWithMaleGender);
    }

    @Test
    public void test009() { // Test to extract all statuses
        List<String> allStatuses = response.extract().path("status");
        System.out.println("The List Of All Statuses : " + allStatuses);
    }

    @Test
    public void test010() { // Test to extract email of a name
        System.out.println("The Email Of Kirti Chaturvedi : " + response.extract().path("findAll{it.name == 'Kirti Chaturvedi'}.email"));
    }
    @Test
    public void test011(){ // Test to extract gender of ID 2250473
        System.out.println("The Gender Of ID 2250473 : " + response.extract().path("findAll{it.id == 2250473}.gender"));
    }
}
